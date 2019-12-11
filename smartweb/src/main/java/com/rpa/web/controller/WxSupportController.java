package com.rpa.web.controller;

import com.rpa.common.constant.Constant;
import com.rpa.web.dto.AdminUserDTO;
import com.rpa.web.dto.WxSupportDTO;
import com.rpa.web.service.IWxSupportService;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.common.vo.ResultVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/9/30 11:10
 * @description: 微信白名单
 * @version: 1.0
 */
@RequestMapping("wxsupport")
@RestController
public class WxSupportController {

    @Resource
    private IWxSupportService service;

    @RequestMapping("query")
    public DTPageInfo<WxSupportDTO> query(@RequestParam(value = "draw", defaultValue = "1") int draw,
                                          @RequestParam(value = "start", defaultValue = "1") int pageNum,
                                          @RequestParam(value = "length", defaultValue = "10") int pageSize,
                                          @RequestParam(value = "packageName") String name) {
        Map<String, Object> reqData = new HashMap<>(1);
        reqData.put("name", name);

        return service.query(draw, pageNum, pageSize, reqData);
    }

    @RequestMapping("insert")
    public ResultVO insert(@RequestParam(value = "packageName") String packageName,
                           @RequestParam(value = "extra") String extra, HttpServletRequest req) {
        // 从Session里获取管理员Id
        AdminUserDTO admin = (AdminUserDTO) req.getSession().getAttribute(Constant.ADMIN_USER);
        if (admin == null) {
            return new ResultVO(1001);
        }
        return service.insert(packageName, extra, admin.getaId());
    }

    @RequestMapping("delete")
    public int delete(@RequestParam(value = "wId") int wId) {
        return service.delete(wId);
    }
}
