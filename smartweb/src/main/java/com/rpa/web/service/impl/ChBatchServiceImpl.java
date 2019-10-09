package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.rpa.web.dto.ChBatchDTO;
import com.rpa.web.mapper.BatchInfoMapper;
import com.rpa.web.mapper.ChBatchMapper;
import com.rpa.web.pojo.BatchInfoPO;
import com.rpa.web.pojo.ChBatchPO;
import com.rpa.web.service.ChBatchService;
import com.rpa.web.utils.DTPageInfo;
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

    /**
     * 查询
     * @param draw
     * @param pageNum
     * @param pageSize
     * @param chanNickname
     * @param comTypeName
     * @param status
     * @param operator
     * @return
     */
    @Override
    public DTPageInfo<ChBatchDTO> query(int draw, int pageNum, int pageSize, String chanNickname, String comTypeName, String status, String operator) {

        // 分页
        Page<ChBatchDTO> page = PageHelper.startPage(pageNum, pageSize);

        // 创建map对象，封装查询条件，作为动态sql语句的参数
        Map<String, Object> map = new HashMap<>(2);
        map.put("chanNickname", chanNickname);
        map.put("comTypeName", comTypeName);
        map.put("status", status);
        map.put("operator", operator);

        // 按照条件查询数据
        List<ChBatchPO> lists_PO = chBatchMapper.query(map);

        // 将查询到的 ChannelPO 数据转换为 ChannelDTO
        List<ChBatchDTO> lists_DTO = new ArrayList<>();
        for(ChBatchPO po: lists_PO) {
            ChBatchDTO dto = new ChBatchDTO();
            dto.setChanNickname(po.getChanNickname());
            dto.setChanName(po.getChanName());
            dto.setCreateTime(po.getCreateTime());
            dto.setCreater(queryUsernameByAid(po.getaId()));
            dto.setComTypeName(po.getComTypeName());
            dto.setNum(po.getNum());
            dto.setActivity(po.getActivity());
            dto.setNonActivity(po.getNonActivity());
            dto.setExtra(po.getExtra());
            dto.setStatus(po.getStatus());
            dto.setUpdateTime(new Date());
            dto.setOperator(po.getOperator());

            lists_DTO.add(dto);
        }

        //根据分页查询的结果，封装最终的返回结果
        return new DTPageInfo<>(draw, page.getTotal(), lists_DTO);
    }

    /**
     * 插入
     * @param chBatchDTO
     * @param httpSession
     * @return
     * @TODO 缺少管理员creater、更新管理员operator这两个字段，值需从session中取
     */
    @Override
    @Transactional(rollbackFor = {})
    public int insert(ChBatchDTO chBatchDTO, HttpSession httpSession) {

        // 把 chBatchDTO 转换为 chBatchPO
        ChBatchPO chBatchPO = new ChBatchPO();
        chBatchPO.setChanId(chBatchDTO.getChanId());
        chBatchPO.setNum(chBatchDTO.getNum());
        chBatchPO.setComTypeId(chBatchDTO.getComTypeId());
        chBatchPO.setComTypeName(queryTypenameByTypeid(chBatchDTO.getComTypeId()));
        chBatchPO.setExtra(chBatchDTO.getExtra());
        chBatchPO.setStatus((byte)1);
        chBatchPO.setDr((byte)1);
        chBatchPO.setCreateTime(new Date());
        chBatchPO.setUpdateTime(new Date());

        //缺少管理员creater、更新管理员operator这两个字段，值需从session中取

        int count = this.chBatchMapper.insert(chBatchPO);

        /**
         * 根据t_ch_batch表中插入数据的num字段，往t_batch_info表中插入num条数据
         */
        List<BatchInfoPO> batchInfoPOs = new ArrayList<>(chBatchDTO.getNum());
        for(int i=0; i<chBatchDTO.getNum(); i++) {
            BatchInfoPO batchInfoPO = new BatchInfoPO();

            //生成一个随机字符串作为卡密
            String str = RandomStringUtils.randomAlphanumeric(16);

            batchInfoPO.setVipkey(str);
            batchInfoPO.setBatchId(chBatchPO.getBatchId());
            batchInfoPO.setStatus((byte)2);
            batchInfoPO.setDays(queryDaysByBatchId(chBatchPO.getBatchId()));
            batchInfoPO.setUpdateTime(new Date());

            batchInfoPOs.add(batchInfoPO);
        }

        this.batchInfoMapper.insertBatchInfo(batchInfoPOs);

        return count;
    }


    /**
     * 修改
     * @param chBatchDTO
     * @return
     * @TODO 缺乏操作人operator字段，需从session中获取
     */
    @Override
    @Transactional(rollbackFor = {})
    public int update(ChBatchDTO chBatchDTO, HttpSession httpSession) {

        // 根据batch_id，从表t_ch_batch中查询出要修改的数据
        ChBatchPO chBatchPO = this.chBatchMapper.selectByPrimaryKey(chBatchDTO.getBatchId());
        chBatchPO.setStatus(chBatchDTO.getStatus());
        chBatchPO.setUpdateTime(new Date());

        //缺乏操作人operator字段，需从session中获取

        int count = chBatchMapper.updateByPrimaryKey(chBatchPO);


        /**
         * 根据t_ch_batch表中所修改的status，修改表t_batch_info相应数据（根据batch_id）的status
         */
        this.batchInfoMapper.updateStatusByBatchId(chBatchDTO.getStatus(), chBatchDTO.getBatchId());

        return count;
    }


    /**
     * 根据aId，从t_admin_user表中查询username
     * @param aId
     * @return
     */
    private String queryUsernameByAid(Integer aId) {
        return this.chBatchMapper.queryUsernameByAid(aId);
    }

    /**
     * 根据com_type_id，从t_com_type中获取com_type_name
     * @param comTypeId
     * @return
     */
    private String queryTypenameByTypeid(Integer comTypeId) {
        return this.chBatchMapper.queryTypenameByTypeid(comTypeId);
    }


    /**
     * 根据batch_id和com_type_id，从t_com_type表中查出days
     * @param batchId
     * @return
     */
    private Integer queryDaysByBatchId(Integer batchId) {
        int comTypeId = this.chBatchMapper.queryTypeIdByBatchId(batchId);
        return this.chBatchMapper.queryDaysByTypeId(comTypeId);
    }
}
