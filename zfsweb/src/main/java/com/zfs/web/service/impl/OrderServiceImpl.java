package com.zfs.web.service.impl;

import com.github.pagehelper.Page;
import com.zfs.common.bo.OrderBO;
import com.zfs.common.bo.UserBO;
import com.zfs.common.mapper.OrderMapper;
import com.zfs.common.vo.PageInfoVO;
import com.zfs.common.vo.ResultVO;
import com.zfs.web.common.PageHelper;
import com.zfs.web.service.IOrderService;
import com.zfs.web.utils.DateUtil;
import com.zfs.web.utils.ExcelUtil;
import com.zfs.web.vo.OrderVO;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/10/6 14:01
 * @description: 订单信息
 * @version: 1.0
 */
@Service
public class OrderServiceImpl implements IOrderService {

    @Resource
    private OrderMapper orderMapper;


    @Override
    public ResultVO query(Integer pageNum, Integer pageSize, Map<String, Object> reqData) {
        Page<OrderBO> page = PageHelper.startPage(pageNum, pageSize);
        List<OrderBO> bos = orderMapper.query(reqData);
        return new ResultVO(1000, new PageInfoVO<>(page.getTotal(), OrderVO.convert(bos)));
    }

    @Override
    public ResultVO export(HttpServletResponse response) {
        List<OrderBO> bos = orderMapper.query(new HashMap<>());

        HSSFWorkbook wb = this.toExcel(bos);
        ExcelUtil.sendToClient(wb, response);

        return new ResultVO(1000);
    }

    /**
     * 生成Excel表格
     *
     * @param bos
     */
    private HSSFWorkbook toExcel(List<OrderBO> bos) {
        //表头
        String[] title = {"序号", "订单编号", "用户渠道", "支付方式", "购买账号", "发起时间", "支付时间", "产品类型", "会员天数", "原价", "折扣", "支付金额"};
        //sheet表名
        String sheetname = "会员订单信息";

        String[][] content = new String[bos.size()][title.length];
        for (int i = 0; i < bos.size(); i++) {
            OrderBO bo = bos.get(i);
            content[i][0] = String.valueOf(i + 1);
            content[i][1] = bo.getOrderNumber();
            content[i][2] = bo.getUserChanName();
            switch (bo.getType()){
                case 1:
                    content[i][3] = "微信";
                    break;
                case 2:
                    content[i][3] = "支付宝";
                    break;
            }
            content[i][4] = bo.getPhone();
            content[i][5] = DateUtil.date2str(bo.getCreateTime());
            content[i][6] = DateUtil.date2str(bo.getPayTime());
            content[i][7] = bo.getComName();
            content[i][8] = String.valueOf(bo.getDays());
            content[i][9] = String.valueOf(bo.getPrice());
            content[i][10] = bo.getShowDiscount();
            content[i][11] = String.valueOf(bo.getDiscount());

        }

        //创建HSSFWorkbook
        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetname, title, content, null);
        return wb;
    }
}
