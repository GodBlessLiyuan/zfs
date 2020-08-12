package com.zfs.web.service.impl;

import com.github.pagehelper.Page;
import com.zfs.common.bo.BatchInfoBO;
import com.zfs.common.vo.PageInfoVO;
import com.zfs.common.vo.ResultVO;
import com.zfs.web.common.PageHelper;
import com.zfs.common.mapper.BatchInfoMapper;
import com.zfs.web.service.BatchInfoService;
import com.zfs.web.utils.DTPageInfo;
import com.zfs.web.utils.DateUtil;
import com.zfs.web.utils.ExcelUtil;
import com.zfs.web.vo.BatchInfoVO;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @author: dangyi
 * @date: Created in 19:51 2019/10/8
 * @version: 1.0.0
 * @description:
 */

@Service
public class BatchInfoServiceImpl implements BatchInfoService {

    @Resource
    private BatchInfoMapper batchInfoMapper;

    /**
     * 查询
     * @param start
     * @param length
     * @param vipkey
     * @return
     */
    @Override
    public ResultVO query( int start, int length, String vipkey) {

        // 分页
        Page<BatchInfoVO> page = PageHelper.startPage(start, length);

        // 创建map对象，封装查询条件，作为动态sql语句的参数
        Map<String, Object> map = new HashMap<>(1);
        map.put("vipkey", vipkey);

        // 按照条件查询数据
        List<BatchInfoBO> bos = batchInfoMapper.query(map);

        // 将查询到的 BatchInfoPO 数据转换为 BatchInfoDTO
        List<BatchInfoVO> vos = new ArrayList<>();
        for(BatchInfoBO bo: bos) {
            BatchInfoVO vo = new BatchInfoVO();
            vo.setVipkey(bo.getVipkey());
            vo.setChanNickname(bo.getChanNickname());
            vo.setChanName(bo.getChanName());
            vo.setCreateTime(bo.getCreateTime());
            vo.setComTypeName(bo.getComTypeName());
            vo.setStatus(bo.getStatus());

            vos.add(vo);
        }

        //根据分页查询的结果，封装最终的返回结果
        return new ResultVO(1000,new PageInfoVO<>(page.getTotal(), vos));
    }


    /**
     * 根据batchId，查询详情，分页展示
     * @param start
     * @param length
     * @param batchId
     * @return
     */
    @Override
    public ResultVO queryByBatchid( int start, int length, Integer batchId, Byte status) {

        // 分页
        Page<BatchInfoVO> page = PageHelper.startPage(start, length);

        // 查询数据
        List<BatchInfoVO> vos = this.query(batchId, status);

        //根据分页查询的结果，封装最终的返回结果
        return new ResultVO(1000,new PageInfoVO<>(page.getTotal(), vos));
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
        List<BatchInfoVO> vos = this.query(batchId, status);

        //生成Excel表格
        HSSFWorkbook wb = this.toExcel(vos);

        //发送响应流数据给前端
        ExcelUtil.sendToClient(wb, response);

    }


    /**
     * 查询数据
     * @param batchId
     * @param status
     * @return
     */
    private List<BatchInfoVO> query(Integer batchId, Byte status) {
        // 创建map对象，封装查询条件，作为动态sql语句的参数
        Map<String, Object> map = new HashMap<>(2);
        map.put("batchId", batchId);
        map.put("status", status);

        // 按照条件查询数据
        List<BatchInfoBO> bos = batchInfoMapper.queryByBatchid(map);

        // 将查询到的 bo 数据转换为 vo
        List<BatchInfoVO> vos = new ArrayList<>();
        for(BatchInfoBO bo : bos) {
            BatchInfoVO vo = bo2vo(bo);
            vos.add(vo);
        }

        return vos;
    }

    /**
     * 将do转换为dto
     * @param bo
     * @return
     */
    private BatchInfoVO bo2vo(BatchInfoBO bo) {
        BatchInfoVO vo = new BatchInfoVO();
        vo.setVipkey(bo.getVipkey());
        vo.setStatus(bo.getStatus());
        /***
         * 只有激活才有激活时间，其他没有
         * */
        if(bo.getStatus()==2){
            vo.setUpdateTime(bo.getUpdateTime());
        }
        vo.setPhone(bo.getPhone());
        return vo;
    }


    /**
     * 生成Excel表格
     * @param vos
     */
    private HSSFWorkbook toExcel(List<BatchInfoVO> vos) {
        //表头
        String[] title = {"会员卡ID", "激活状态", "激活时间", "激活账号"};
        //sheet表名
        String sheetname = "会员卡详情";

        String status = "";

        String[][] content = new String[vos.size()][title.length];
        for (int i = 0; i < vos.size(); i++) {
            BatchInfoVO vo = vos.get(i);
            content[i][0] = vo.getVipkey();
            if (vo.getStatus() == 1) {
                status = "未激活";
            } else if (vo.getStatus() == 2) {
                status = "已激活";
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
            content[i][1] = status;
            if (null == vo.getUpdateTime()) {
                content[i][2] = "";
            } else {
                content[i][2] = DateUtil.date2str(vo.getUpdateTime());
            }
            content[i][3] = vo.getPhone();
        }

        //创建HSSFWorkbook
        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetname, title, content, null);
        return wb;
    }
}
