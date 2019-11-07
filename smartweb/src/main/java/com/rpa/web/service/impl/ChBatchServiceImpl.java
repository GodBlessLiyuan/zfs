package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.rpa.web.common.Constant;
import com.rpa.web.common.PageHelper;
import com.rpa.web.dto.AdminUserDTO;
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
import com.rpa.web.utils.ResultVOUtil;
import com.rpa.web.vo.ResultVO;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

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
     * 查询
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

            DTOs.add(dto);
        }

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
        po.setaId(aId);//测试的时候，暂且写为1，正常参数应为aId
        po.setUpdateAId(aId);//测试的时候，暂且写为1，正常参数应为aId

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
        po.setUpdateAId(aId);//测试的时候，暂且写为1，正常参数应为aId

        int count = chBatchMapper.updateByPrimaryKey(po);


        /**
         * 根据t_ch_batch表中所修改的status，修改表t_batch_info中相应数据（根据batch_id）的状态值
         * t_batch_info表中，状态为已激活的（值为2），不用再管了
         */

        this.batchInfoMapper.updateStatusByBatchId(status, batchId);

        return count == 1 ? ResultVOUtil.success() : ResultVOUtil.error(ExceptionEnum.UPDATE_ERROR);
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
}
