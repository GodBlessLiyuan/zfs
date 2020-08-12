package com.zfs.web.controller;

import com.zfs.common.constant.Constant;
import com.zfs.common.vo.ResultVO;
import com.zfs.web.dto.AdminUserDTO;
import com.zfs.web.service.IVipCommodityService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/9/26 11:49
 * @description: 商品列表
 * @version: 1.0
 */
@RequestMapping("vipcommodity")
@RestController
public class VipCommodityController {

    @Resource
    private IVipCommodityService service;

    @RequestMapping("query")
    public ResultVO query(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                          @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                          @RequestParam(value = "username", required = false) String username,
                          @RequestParam(value = "comTypeId", required = false) Integer comTypeId,
                          @RequestParam(value = "channelId", required = false) Integer channelId) {

        Map<String, Object> reqData = new HashMap<>(3);
        reqData.put("username", username);
        reqData.put("comTypeId", comTypeId);
        reqData.put("channelId", channelId);

        return service.query(pageNum, pageSize, reqData);
    }

    @RequestMapping("queryById")
    public ResultVO queryById(@RequestParam(value = "cmdyId") Integer cmdyId) {
        return service.queryById(cmdyId);
    }

    @RequestMapping("insert")
    public ResultVO insert(
            @RequestParam(value = "commAttr",defaultValue = "1") Integer commAttr,
            @RequestParam(value = "channelId",defaultValue = "1") Integer channelId,
            @RequestParam(value = "comTypeId") int comTypeId,
            @RequestParam(value = "comName") String comName,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "price") String price,
            @RequestParam(value = "showDiscount") String showDiscount,
            @RequestParam(value = "discount") float discount, HttpServletRequest req) {

        // 从Session里获取管理员Id
        AdminUserDTO admin = (AdminUserDTO) req.getSession().getAttribute(Constant.ADMIN_USER);
        if (admin == null) {
            return new ResultVO(1001);
        }
        /**
         * 商品跟渠道没有关系，构造虚假数据为了跟原来的业务代码一致。
         * commAttr:为1表示独立商品
         * */
        return service.insert(commAttr, channelId, comTypeId, comName, description, price, showDiscount,
                discount, admin.getaId());

    }

    @RequestMapping("update")
    public ResultVO update(@RequestParam(value = "cmdyId") Integer cmdyId,
                           @RequestParam(value = "comName") String comName,
                           @RequestParam(value = "description",required = false) String description,
                           @RequestParam(value = "price",required = false) String price,
                           @RequestParam(value = "showDiscount",required = false) String showDiscount,
                           @RequestParam(value = "discount") Float discount) {

        return service.update(cmdyId, comName, description, price, showDiscount, discount);
    }

    @RequestMapping("updateStatus")
    public ResultVO updateStatus(@RequestParam(value = "cmdyId") Integer cmdyId,
                                 @RequestParam(value = "status") Byte status) {
        return service.updateStatus(cmdyId, status);
    }

    @RequestMapping("updateIsTop")
    public ResultVO updateIsTop(@RequestParam(value = "cmdyId") Integer cmdyId,
                                @RequestParam(value = "isTop") Byte isTop) {
        return service.updateIsTop(cmdyId, isTop);
    }

    @RequestMapping("export")
    public ResultVO export(HttpServletResponse response) {
        return service.export(response);
    }
}
