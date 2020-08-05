package com.zfs.web.controller;

import com.zfs.common.constant.Constant;
import com.zfs.web.dto.AdminUserDTO;
import com.zfs.web.vo.WhiteDeviceVO;
import com.zfs.web.service.IWhiteDeviceService;
import com.zfs.web.utils.DTPageInfo;
import com.zfs.common.vo.ResultVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/9/30 14:20
 * @description: 测试白名单
 * @version: 1.0
 */
@RequestMapping("whiltedevice")
@RestController
public class WhiteDeviceController {

    @Resource
    private IWhiteDeviceService service;

    @RequestMapping("query")
    public DTPageInfo<WhiteDeviceVO> query(@RequestParam(value = "draw", defaultValue = "1") int draw,
                                           @RequestParam(value = "start", defaultValue = "1") int pageNum,
                                           @RequestParam(value = "length", defaultValue = "10") int pageSize,
                                           @RequestParam(value = "imei") String imei) {
        Map<String, Object> reqData = new HashMap<>(1);
        reqData.put("imei", imei);

        return service.query(draw, pageNum, pageSize, reqData);
    }

    @RequestMapping("insert")
    public ResultVO insert(@RequestParam(value = "imei") String imei,
                           @RequestParam(value = "extra") String extra, HttpServletRequest req) {
        // 从Session里获取管理员Id
        AdminUserDTO admin = (AdminUserDTO) req.getSession().getAttribute(Constant.ADMIN_USER);
        if (admin == null) {
            return new ResultVO(1001);
        }
        return service.insert(imei, extra, admin.getaId());
    }

    @RequestMapping("delete")
    public int delete(@RequestParam(value = "deviceId") int deviceId) {
        return service.deleteByDeviceId(deviceId);
    }
}
