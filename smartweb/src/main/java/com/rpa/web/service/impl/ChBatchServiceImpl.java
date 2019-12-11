package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.rpa.common.mapper.AdminUserMapper;
import com.rpa.web.common.Constant;
import com.rpa.web.common.PageHelper;
import com.rpa.common.dto.AdminUserDTO;
import com.rpa.web.dto.ChBatchDTO;
import com.rpa.web.dto.ChannelDTO;
import com.rpa.web.dto.ComTypeDTO;
import com.rpa.web.enumeration.ExceptionEnum;
import com.rpa.web.mapper.*;
import com.rpa.web.pojo.BatchInfoPO;
import com.rpa.web.pojo.ChBatchPO;
import com.rpa.web.pojo.ChannelPO;
import com.rpa.web.pojo.ComTypePO;
import com.rpa.web.service.ChBatchService;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.web.utils.DateUtil;
import com.rpa.web.utils.ExcelUtil;
import com.rpa.common.vo.ResultVO;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private ChBatchMapper chBatchMapper;

    @Autowired
    private BatchInfoMapper batchInfoMapper;

    @Autowired
    private ComTypeMapper comTypeMapper;

    @Autowired
    private AdminUserMapper adminUserMapper;

    @Autowired
    private ChannelMapper channelMapper;


    /**
     * 分页查询
     * @param draw
     * @param start
     * @param length
     * @param chanNickname
     * @param comTypeId
     * @param status
     * @param operator
     * @return
     */
    @Override
    public DTPageInfo<ChBatchDTO> query(int draw, int start, int length, String chanNickname, Integer comTypeId, Byte status, String operator) {

        // 分页
        Page<ChBatchDTO> page = PageHelper.startPage(start, length);

        //查询数据
        List<ChBatchDTO> DTOs = this.query(chanNickname, comTypeId, status, operator);

        //根据分页查询的结果，封装最终的返回结果
        return new DTPageInfo<>(draw, page.getTotal(), DTOs);
    }



    /**
     * 查询所有的产品类型
     * @return
     */
    @Override
    public ResultVO queryComTypes() {

        List<ComTypePO> POs = this.comTypeMapper.queryAll();

        if (null == POs) {
            return ResultVOUtil.success("");
        }

        // 将 po 转换为 dto
        List<ComTypeDTO> DTOs = new ArrayList<>();
        for (ComTypePO po : POs) {
            ComTypeDTO dto = new ComTypeDTO();
            dto.setComTypeId(po.getComTypeId());
            dto.setName(po.getName());
            DTOs.add(dto);
        }
        return ResultVOUtil.success(DTOs);
    }


    /**
     * 查询所有渠道标识
     * @return
     */
    @Override
    public ResultVO queryChanNicknames() {

        List<ChannelPO> POs = this.channelMapper.queryChanNicknames();

        if (null == POs) {
            return ResultVOUtil.success("");
        }

        // 将 po 转换为 dto
        List<ChannelDTO> DTOs = new ArrayList<>();
        for (ChannelPO po : POs) {
            ChannelDTO dto = new ChannelDTO();
            dto.setChanId(po.getChanId());
            dto.setChanNickname(po.getChanNickname());
            DTOs.add(dto);
        }
        return ResultVOUtil.success(DTOs);
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

        // 从session中获取当前用户的a_id
        // 能从session中获取用户的信息，说明当前用户是登录状态
        AdminUserDTO adminUserDTO = (AdminUserDTO) httpSession.getAttribute(Constant.ADMIN_USER);
        int aId = adminUserDTO.getaId();

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

        // 创建时，创建人和更操作新人一样，此后创建人ID便不再改变
        po.setaId(aId);
        po.setUpdateAId(aId);

        int count = this.chBatchMapper.insert(po);


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

        this.batchInfoMapper.insertBatchInfo(batchInfoPOs);

        return count == 1 ? ResultVOUtil.success() : ResultVOUtil.error(ExceptionEnum.INSERT_ERROR);
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

        // 从session中获取当前用户的a_id
        // 能从session中获取用户的信息，说明当前用户是登录状态
        AdminUserDTO adminUserDTO = (AdminUserDTO) httpSession.getAttribute(Constant.ADMIN_USER);
        int aId = adminUserDTO.getaId();

        // 查询出要修改的数据
        ChBatchPO po = this.chBatchMapper.selectByPrimaryKey(batchId);

        if (null == po) {
            return ResultVOUtil.error(ExceptionEnum.QUERY_ERROR);
        }

        po.setStatus(status);
        po.setUpdateTime(new Date());
        po.setUpdateAId(aId);

        int count = chBatchMapper.updateByPrimaryKey(po);


        /**
         * 根据t_ch_batch表中所修改的status，修改表t_batch_info中相应数据（根据batch_id）的状态值
         * t_batch_info表中，状态为已激活的（值为2），不用再管了
         */

        this.batchInfoMapper.updateStatusByBatchId(status, batchId);

        return count == 1 ? ResultVOUtil.success() : ResultVOUtil.error(ExceptionEnum.UPDATE_ERROR);
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
        List<ChBatchDTO> DTOs = this.query(chanNickname, comTypeId, status, operator);

        //生成Excel表格
        HSSFWorkbook wb = this.toExcel(DTOs);

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
    private List<ChBatchDTO> query(String chanNickname, Integer comTypeId, Byte status, String operator) {

        // 创建map对象，封装查询条件，作为动态sql语句的参数
        Map<String, Object> map = new HashMap<>(4);
        map.put("chanNickname", chanNickname);
        map.put("comTypeId", comTypeId);
        map.put("status", status);
        map.put("operator", operator);

        // 按照条件查询数据
        List<ChBatchPO> POs = chBatchMapper.query(map);
        // 将查询到的 PO 数据转换为 DTO
        List<ChBatchDTO> DTOs = new ArrayList<>();
        for(ChBatchPO po: POs) {
            ChBatchDTO dto = this.po2dto(po);
            DTOs.add(dto);
        }
        return DTOs;
    }


    /**
     * 将po转换为dto
     * @param po
     * @return
     */
    private ChBatchDTO po2dto(ChBatchPO po) {
        ChBatchDTO dto = new ChBatchDTO();
        dto.setBatchId(po.getBatchId());
        dto.setChanNickname(po.getChanNickname());
        dto.setChanName(po.getChanName());
        dto.setCreateTime(po.getCreateTime());
        dto.setCreater(queryUsernameByAid(po.getaId()));
        dto.setComTypeId(po.getComTypeId());
        dto.setComTypeName(po.getComTypeName());
        dto.setNum(po.getNum());
        dto.setActivity(queryStatusById(po.getBatchId(), 2));
        dto.setNonActivity(queryStatusById(po.getBatchId(),1));
        dto.setExtra(po.getExtra());
        dto.setStatus(po.getStatus());
        dto.setUpdateTime(po.getUpdateTime());
        dto.setOperator(queryUsernameByAid(po.getUpdateAId()));
        return dto;
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
    private Integer queryStatusById(Integer batchId, int status) {
        return this.batchInfoMapper.queryStatusById(batchId, status);
    }


    /**
     * 生成Excel表格
     * @param DTOs
     */
    private HSSFWorkbook toExcel(List<ChBatchDTO> DTOs) {
        //表头
        String[] title = {"渠道标识", "渠道名称", "创建时间", "创建人", "产品类型", "创建数量", "已激活",
                "未激活", "备注", "状态", "操作时间", "操作人"};
        //sheet表名
        String sheetname = "会员卡配置";

        String status = "";

        String[][] content = new String[DTOs.size()][title.length];
        for (int i = 0; i < DTOs.size(); i++) {
            ChBatchDTO dto = DTOs.get(i);
            content[i][0] = dto.getChanNickname();
            content[i][1] = dto.getChanName();
            if (null == dto.getCreateTime()) {
                content[i][2] = "";
            } else {
                content[i][2] = DateUtil.date2str(dto.getCreateTime());
            }
            content[i][3] = dto.getCreater();
            content[i][4] = dto.getComTypeName();
            content[i][5] = dto.getNum().toString();
            content[i][6] = dto.getActivity().toString();
            content[i][7] = dto.getNonActivity().toString();
            content[i][8] = dto.getExtra();
            if (dto.getStatus() == 1) {
                status = "正常";
            } else if (dto.getStatus() == 3) {
                status = "已冻结";
            } else if (dto.getStatus() == 4) {
                status = "已失效";
            } else if (dto.getStatus() == 5) {
                status = "已结束";
            }
            content[i][9] = status;
            if (null == dto.getUpdateTime()) {
                content[i][10] = "";
            }else {
                content[i][10] = DateUtil.date2str(dto.getUpdateTime());
            }
            content[i][11] = dto.getOperator();
        }

        //创建HSSFWorkbook
        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetname, title, content, null);
        return wb;
    }
}
