package com.zfs.web.service.impl;

import com.github.pagehelper.Page;
import com.zfs.common.bo.ComTypeBO;
import com.zfs.common.mapper.*;
import com.zfs.common.pojo.ChBatchPO;
import com.zfs.common.pojo.ChannelPO;
import com.zfs.common.utils.DateUtilCard;
import com.zfs.common.utils.LogUtil;
import com.zfs.common.vo.PageInfoVO;
import com.zfs.web.common.PageHelper;
import com.zfs.web.dto.ChBatchDTO;
import com.zfs.common.pojo.BatchInfoPO;
import com.zfs.common.bo.ChBatchBO;
import com.zfs.web.dto.ChBatchSyncDTO;
import com.zfs.web.service.ChBatchService;
import com.zfs.web.utils.DTPageInfo;
import com.zfs.web.utils.DateUtil;
import com.zfs.web.utils.ExcelUtil;
import com.zfs.common.vo.ResultVO;
import com.zfs.web.utils.OperatorUtil;
import com.zfs.web.vo.ChBatchVO;
import com.zfs.web.vo.ChannelVO;
import com.zfs.web.vo.ComTypeVO;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author: dangyi
 * @date: Created in 19:49 2019/10/7
 * @version: 1.0.0
 * @description:
 */
@Service
@EnableTransactionManagement
public class ChBatchServiceImpl implements ChBatchService {
    private final static Logger logger = LoggerFactory.getLogger(ChBatchServiceImpl.class);

    @Resource
    private ChBatchMapper chBatchMapper;

    @Resource
    private BatchInfoMapper batchInfoMapper;

    @Resource
    private ComTypeMapper comTypeMapper;

    @Resource
    private AdminUserMapper adminUserMapper;

    @Resource
    private ChannelMapper channelMapper;


    /**
     * 分页查询
     * @param start
     * @param length
     * @param chanNickname
     * @param comTypeId
     * @param status
     * @param operator
     * @return
     */
    @Override
    public ResultVO query( int start, int length, String chanNickname, Integer comTypeId, Byte status, String operator) {

        // 分页
        Page<ChBatchVO> page = PageHelper.startPage(start, length);

        //查询数据
        List<ChBatchVO> vos = this.query(chanNickname, comTypeId, status, operator);

        //根据分页查询的结果，封装最终的返回结果
        return new ResultVO(1000,new PageInfoVO<>(page.getTotal(), vos));
    }



    /**
     * 查询所有的产品类型
     * @return
     */
    @Override
    public ResultVO queryComTypes() {

        List<ComTypeBO> bos = this.comTypeMapper.queryAll();

        if (null == bos) {
            return new ResultVO(1002);
        }

        // 将 bo 转换为 vo
        List<ComTypeVO> vos = new ArrayList<>();
        for (ComTypeBO bo : bos) {
            ComTypeVO vo = new ComTypeVO();
            vo.setComTypeId(bo.getComTypeId());
            vo.setName(bo.getName());
            vos.add(vo);
        }
        return new ResultVO(1000, vos);
    }


    /**
     * 查询所有渠道标识
     * @return
     */
    @Override
    public ResultVO queryChanNicknames() {

        List<ChannelPO> pos = this.channelMapper.queryChanNicknames();

        if (null == pos) {
            return new ResultVO(1002);
        }

        // 将 po 转换为 vo
        List<ChannelVO> vos = new ArrayList<>();
        for (ChannelPO po : pos) {
            ChannelVO vo = new ChannelVO();
            vo.setChanId(po.getChanId());
            vo.setChanNickname(po.getChanNickname());
            vos.add(vo);
        }
        return new ResultVO(1000, vos);
    }


    /**
     * 插入
     * @param dto
     * @param httpSession
     * @return
     */
    @Override
    @Transactional(rollbackFor = {})
    public ResultVO insert(ChBatchDTO dto, HttpSession httpSession) {

        // 把 DTO 转换为 PO
        ChBatchPO po = new ChBatchPO();
        po.setChanId(dto.getChanId());
        po.setNum(dto.getNum());
        po.setComTypeId(dto.getComTypeId());
        po.setComTypeName(queryTypenameByTypeid(dto.getComTypeId()));
        po.setExtra(dto.getExtra());
        po.setStatus((byte)1);
        po.setDr((byte)1);
        po.setCreateTime(new Date());
        po.setUpdateTime(new Date());
        po.setDays(dto.getDays());

        // 创建时，创建人和更操作新人一样，此后创建人ID便不再改变
        po.setaId(OperatorUtil.getOperatorId(httpSession));
        po.setUpdateAId(OperatorUtil.getOperatorId(httpSession));

        int result1 = this.chBatchMapper.insert(po);
        if (result1 == 0) {
            LogUtil.log(logger, "insert", "插入失败", po);
            return ResultVO.serverInnerError();
        }


        /**
         * 根据t_ch_batch表中插入数据的num字段，往t_batch_info表中插入num条数据
         */
        List<BatchInfoPO> batchInfoPOs = new ArrayList<>(dto.getNum());
        for(int i=0; i<dto.getNum(); i++) {
            BatchInfoPO batchInfoPO = new BatchInfoPO();

            //生成一个随机字符串作为卡密
            String str = RandomStringUtils.randomAlphanumeric(16);

            batchInfoPO.setVipkey(str);
            batchInfoPO.setBatchId(po.getBatchId());
            batchInfoPO.setStatus((byte)1);
            batchInfoPO.setDays(queryDaysByBatchId(po.getBatchId()));
            batchInfoPO.setUserId((long)0);

            batchInfoPOs.add(batchInfoPO);
        }

        int result2 = this.batchInfoMapper.insertBatchInfo(batchInfoPOs);
        if (result2 == 0) {
            LogUtil.log(logger, "insert", "插入batchInfoPOs失败", batchInfoPOs);
            return ResultVO.serverInnerError();
        }

        return new ResultVO(1000);
    }

    /***
     * 因为不想破坏源代码，所以写了一个，只多了一个setActiveSync()
     * */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public ResultVO insertSync(ChBatchSyncDTO dto, HttpSession httpSession) {

        // 把 DTO 转换为 PO
        ChBatchPO po = new ChBatchPO();
        po.setChanId(dto.getChanId());
        po.setNum(dto.getNum());
        po.setComTypeId(dto.getComTypeId());
        po.setComTypeName(queryTypenameByTypeid(dto.getComTypeId()));
        po.setExtra(dto.getExtra());
        po.setStatus((byte)1);
        po.setDr((byte)1);
        po.setCreateTime(new Date());
        po.setUpdateTime(new Date());
        po.setDays(chBatchMapper.queryDaysByTypeId(dto.getComTypeId()));
        po.setActiveSync(dto.getActiveSync());
        // 创建时，创建人和更操作新人一样，此后创建人ID便不再改变
        po.setaId(OperatorUtil.getOperatorId(httpSession));
        po.setUpdateAId(OperatorUtil.getOperatorId(httpSession));

        int result1 = this.chBatchMapper.insert(po);
        if (result1 == 0) {
            LogUtil.log(logger, "insert", "插入失败", po);
        }


        /**
         * 根据t_ch_batch表中插入数据的num字段，往t_batch_info表中插入num条数据
         */
        List<BatchInfoPO> batchInfoPOs = new ArrayList<>(dto.getNum());
        for(int i=0; i<dto.getNum(); i++) {
            BatchInfoPO batchInfoPO = new BatchInfoPO();

            //生成一个随机字符串作为卡密
            String str = RandomStringUtils.randomAlphanumeric(16);

            batchInfoPO.setVipkey(str);
            batchInfoPO.setBatchId(po.getBatchId());
            batchInfoPO.setStatus((byte)1);
            batchInfoPO.setDays(queryDaysByBatchId(po.getBatchId()));
            batchInfoPO.setUserId((long)0);

            batchInfoPOs.add(batchInfoPO);
        }

        int result2 = this.batchInfoMapper.insertBatchInfo(batchInfoPOs);
        if (result2 == 0) {
            LogUtil.log(logger, "insert", "插入batchInfoPOs失败", batchInfoPOs);
        }

        return new ResultVO(1000);
    }

    /**
     * 修改
     * @param batchId
     * @param status
     * @param httpSession
     * @return
     */
    @Override
    @Transactional(rollbackFor = {})
    public ResultVO updateStatus(Integer batchId, Byte status, HttpSession httpSession) {

        // 查询出要修改的数据
        ChBatchPO po = this.chBatchMapper.selectByPrimaryKey(batchId);

        if (null == po) {
            return new ResultVO(1002);
        }

        po.setStatus(status);
        po.setUpdateTime(new Date());
        po.setUpdateAId(OperatorUtil.getOperatorId(httpSession));

        int result1 = chBatchMapper.updateByPrimaryKey(po);
        if (result1 == 0) {
            LogUtil.log(logger, "updateStatus", "更新失败", po);
            return ResultVO.serverInnerError();
        }

        /**
         * 根据t_ch_batch表中所修改的status，修改表t_batch_info中相应数据（根据batch_id）的状态值
         * t_batch_info表中，状态为已激活的（值为2），不用再管了,
         * 如果批次是冻结，解冻下；存在卡密都是2，也就是使用了的激活的卡密的情况。
         */

        int result2 = this.batchInfoMapper.updateStatusByBatchId(status, batchId);


        return new ResultVO(1000);
    }

    /**
     * 导出数据
     * @param chanNickname
     * @param comTypeId
     * @param status
     * @param operator
     * @return
     */
    @Override
    public void export(String chanNickname, Integer comTypeId, Byte status, String operator,
                       HttpServletResponse response) {

        //查询数据
        List<ChBatchVO> vos = this.query(chanNickname, comTypeId, status, operator);

        //生成Excel表格
        HSSFWorkbook wb = this.toExcel(vos);

        //发送响应流数据给前端
        ExcelUtil.sendToClient(wb, response);
    }


    /**
     * 查询数据
     * @param chanNickname
     * @param comTypeId
     * @param status
     * @param operator
     * @return
     */
    private List<ChBatchVO> query(String chanNickname, Integer comTypeId, Byte status, String operator) {

        // 创建map对象，封装查询条件，作为动态sql语句的参数
        Map<String, Object> map = new HashMap<>(4);
        map.put("chanNickname", chanNickname);
        map.put("comTypeId", comTypeId);
        map.put("status", status);
        map.put("operator", operator);

        // 按照条件查询数据
        List<ChBatchBO> pos = this.chBatchMapper.query(map);
        // 将查询到的 po 数据转换为 vo
        List<ChBatchVO> vos = new ArrayList<>();
        for(ChBatchBO po: pos) {
            ChBatchVO vo = this.po2vo(po);
            vos.add(vo);
        }
        return vos;
    }


    /**
     * 将po转换为vo
     * @param po
     * @return
     */
    private ChBatchVO po2vo(ChBatchBO po) {
        ChBatchVO vo = new ChBatchVO();
        vo.setBatchId(po.getBatchId());
        vo.setChanNickname(po.getChanNickname());
        vo.setChanName(po.getChanName());
        vo.setCreateTime(po.getCreateTime());
        vo.setCreater(queryUsernameByAid(po.getaId()));
        vo.setComTypeId(po.getComTypeId());
        vo.setComTypeName(po.getComTypeName());
        vo.setNum(po.getNum());
        vo.setActivity(queryStatusById(po.getBatchId(), 2));
        vo.setNonActivity(queryStatusById(po.getBatchId(),1));
        vo.setExtra(po.getExtra());
        vo.setStatus(po.getStatus());
        vo.setUpdateTime(po.getUpdateTime());
        vo.setOperator(queryUsernameByAid(po.getUpdateAId()));
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(po.getCreateTime());
        calendar.add(Calendar.DATE,po.getDays());
        vo.setValidityTime(DateUtilCard.date2Str(calendar.getTime(),DateUtilCard.YMD));
        return vo;
    }

    /**
     * 根据aId，从t_admin_user表中查询username
     * @param aId
     * @return
     */
    private String queryUsernameByAid(Integer aId) {
        return this.adminUserMapper.queryUsernameByAid(aId);
    }

    /**
     * 根据com_type_id，从t_com_type中获取com_type_name
     * @param comTypeId
     * @return
     */
    private String queryTypenameByTypeid(Integer comTypeId) {
        return this.comTypeMapper.queryTypenameByTypeid(comTypeId);
    }


    /**
     * 根据batch_id和com_type_id，从t_com_type表中查出days
     * @param batchId
     * @return
     */
    private Integer queryDaysByBatchId(Integer batchId) {
        Integer comTypeId = this.chBatchMapper.queryTypeIdByBatchId(batchId);
        return this.chBatchMapper.queryDaysByTypeId(comTypeId);
    }

    /**
     * 根据batch_id和状态值，往t_batch_info表中查询激活和非激活状态的数量
     * @param batchId
     * @param status
     * @return
     */
    private Integer queryStatusById(int batchId, int status) {
        return this.batchInfoMapper.queryStatusById(batchId, status);
    }


    /**
     * 生成Excel表格
     * @param vos
     */
    private HSSFWorkbook toExcel(List<ChBatchVO> vos) {
        //表头
        String[] title = {"渠道标识", "渠道名称", "创建时间", "创建人", "产品类型", "创建数量", "已激活",
                "激活有效期至", "备注", "状态", "操作时间", "操作人"};
        //sheet表名
        String sheetname = "会员卡配置";

        String status = "";

        String[][] content = new String[vos.size()][title.length];
        for (int i = 0; i < vos.size(); i++) {
            ChBatchVO vo = vos.get(i);
            content[i][0] = vo.getChanNickname();
            content[i][1] = vo.getChanName();
            if (null == vo.getCreateTime()) {
                content[i][2] = "";
            } else {
                content[i][2] = DateUtil.date2str(vo.getCreateTime());
            }
            content[i][3] = vo.getCreater();
            content[i][4] = vo.getComTypeName();
            content[i][5] = vo.getNum()==null?"":vo.getNum().toString();
            content[i][6] = vo.getActivity()==null?"":vo.getNum().toString();
            //由未激活数量getNonActivity().toString();改为有效期至
            content[i][7] = vo.getValidityTime();
            content[i][8] = vo.getExtra();
            if (vo.getStatus() == 1) {
                status = "正常";
            } else if (vo.getStatus() == 3) {
                status = "已冻结";
            } else if (vo.getStatus() == 4) {
                status = "已失效";
            } else if (vo.getStatus() == 5) {
                status = "已结束";
            } else if(vo.getStatus()==6){
                status="过期失效";
            } else if(vo.getStatus()==7){
                status="冻结失效";
            }
            content[i][9] = status;
            if (null == vo.getUpdateTime()) {
                content[i][10] = "";
            }else {
                content[i][10] = DateUtil.date2str(vo.getUpdateTime());
            }
            content[i][11] = vo.getOperator();
        }

        //创建HSSFWorkbook
        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetname, title, content, null);
        return wb;
    }
}
