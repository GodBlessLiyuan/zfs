package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.rpa.web.common.PageHelper;
import com.rpa.web.domain.BatchInfoDO;
import com.rpa.web.dto.BatchInfoDTO;
import com.rpa.web.mapper.BatchInfoMapper;
import com.rpa.web.service.BatchInfoService;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.web.utils.DateUtil;
import com.rpa.web.utils.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author: dangyi
 * @date: Created in 19:51 2019/10/8
 * @version: 1.0.0
 * @description:
 */

@Service
public class BatchInfoServiceImpl implements BatchInfoService {

    @Autowired
    private BatchInfoMapper batchInfoMapper;

    /**
     * 查询
     * @param draw
     * @param start
     * @param length
     * @param vipkey
     * @return
     */
    @Override
    public DTPageInfo<BatchInfoDTO> query(int draw, int start, int length, String vipkey) {

        // 分页
        Page<BatchInfoDTO> page = PageHelper.startPage(start, length);

        // 创建map对象，封装查询条件，作为动态sql语句的参数
        Map<String, Object> map = new HashMap<>(1);
        map.put("vipkey", vipkey);

        // 按照条件查询数据
        List<BatchInfoDO> lists_PO = batchInfoMapper.query(map);

        // 将查询到的 BatchInfoPO 数据转换为 BatchInfoDTO
        List<BatchInfoDTO> lists_DTO = new ArrayList<>();
        for(BatchInfoDO po: lists_PO) {
            BatchInfoDTO dto = new BatchInfoDTO();
            dto.setVipkey(po.getVipkey());
            dto.setChanNickname(po.getChanNickname());
            dto.setChanName(po.getChanName());
            dto.setCreateTime(po.getCreateTime());
            dto.setComTypeName(po.getComTypeName());
            dto.setStatus(po.getStatus());

            lists_DTO.add(dto);
        }

        //根据分页查询的结果，封装最终的返回结果
        return new DTPageInfo<>(draw, page.getTotal(), lists_DTO);
    }


    /**
     * 根据batchId，查询详情，分页展示
     * @param draw
     * @param start
     * @param length
     * @param batchId
     * @return
     */
    @Override
    public DTPageInfo<BatchInfoDTO> queryByBatchid(int draw, int start, int length, Integer batchId, Byte status) {

        // 分页
        Page<BatchInfoDTO> page = PageHelper.startPage(start, length);

        // 查询数据
        List<BatchInfoDTO> DTOs = this.query(batchId, status);

        //根据分页查询的结果，封装最终的返回结果
        return new DTPageInfo<>(draw, page.getTotal(), DTOs);
    }

    /**
     * 导出数据
     * @param batchId
     * @param status
     * @param response
     */
    @Override
    public void export(Integer batchId, Byte status, HttpServletResponse response) {
        // 查询数据
        List<BatchInfoDTO> DTOs = this.query(batchId, status);

        //生成Excel表格
        HSSFWorkbook wb = this.toExcel(DTOs);

        //发送响应流数据给前端
        ExcelUtil.sendToClient(wb, response);

    }


    /**
     * 查询数据
     * @param batchId
     * @param status
     * @return
     */
    private List<BatchInfoDTO> query(Integer batchId, Byte status) {
        // 创建map对象，封装查询条件，作为动态sql语句的参数
        Map<String, Object> map = new HashMap<>(2);
        map.put("batchId", batchId);
        map.put("status", status);

        // 按照条件查询数据
        List<BatchInfoDO> lists_DO = batchInfoMapper.queryByBatchid(map);

        // 将查询到的 BatchInfoDO 数据转换为 BatchInfoDTO
        List<BatchInfoDTO> DTOs = new ArrayList<>();
        for(BatchInfoDO batchInfoDO: lists_DO) {
            BatchInfoDTO dto = do2dto(batchInfoDO);
            DTOs.add(dto);
        }

        return DTOs;
    }

    /**
     * 将do转换为dto
     * @param batchInfoDO
     * @return
     */
    private BatchInfoDTO do2dto(BatchInfoDO batchInfoDO) {
        BatchInfoDTO dto = new BatchInfoDTO();
        dto.setVipkey(batchInfoDO.getVipkey());
        dto.setStatus(batchInfoDO.getStatus());
        dto.setUpdateTime(batchInfoDO.getUpdateTime());
        dto.setPhone(batchInfoDO.getPhone());
        return dto;
    }


    /**
     * 生成Excel表格
     * @param DTOs
     */
    private HSSFWorkbook toExcel(List<BatchInfoDTO> DTOs) {
        //表头
        String[] title = {"会员卡ID", "激活状态", "激活时间", "激活账号"};
        //sheet表名
        String sheetname = "会员卡详情";

        String status = "";

        String[][] content = new String[DTOs.size()][title.length];
        for (int i = 0; i < DTOs.size(); i++) {
            BatchInfoDTO dto = DTOs.get(i);
            content[i][0] = dto.getVipkey();
            if (dto.getStatus() == 1) {
                status = "未激活";
            } else if (dto.getStatus() == 2) {
                status = "已激活";
            } else if (dto.getStatus() == 3) {
                status = "已冻结";
            } else if (dto.getStatus() == 4) {
                status = "已失效";
            }
            content[i][1] = status;
            if (null == dto.getUpdateTime()) {
                content[i][2] = "";
            } else {
                content[i][2] = DateUtil.date2str(dto.getUpdateTime());
            }
            content[i][3] = dto.getPhone();
        }

        //创建HSSFWorkbook
        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetname, title, content, null);
        return wb;
    }
}
