package com.rpa.web.controller;

import com.rpa.common.constant.Constant;
import com.rpa.common.dto.AdminUserDTO;
import com.rpa.web.dto.OtherAppDTO;
import com.rpa.web.service.IOtherAppService;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.common.vo.ResultVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/9/29 20:01
 * @description: 其他产品
 * @version: 1.0
 */
@RequestMapping("otherapp")
@RestController
public class OtherAppController {

    @Resource
    private IOtherAppService service;

    @RequestMapping("query")
    public DTPageInfo<OtherAppDTO> query(@RequestParam(value = "draw", defaultValue = "1") int draw,
                                         @RequestParam(value = "start", defaultValue = "1") int pageNum,
                                         @RequestParam(value = "length", defaultValue = "10") int pageSize,
                                         @RequestParam(value = "name") String name) {
        Map<String, Object> reqData = new HashMap<>(1);
        reqData.put("name", name);

        return service.query(draw, pageNum, pageSize, reqData);
    }

    @PostMapping("insert")
    public ResultVO insert(@RequestParam(value = "oName") String oName,
                           @RequestParam(value = "extra") String extra,
                           @RequestParam(value = "iconUrl") MultipartFile iconUrl,
                           @RequestParam(value = "downloadType") byte downloadType,
                           @RequestParam(value = "appUrl") String appUrl,
                           @RequestParam(value = "apkUrl", required = false) MultipartFile apkUrl,
                           HttpServletRequest req) {
        // 从Session里获取管理员Id
        AdminUserDTO admin = (AdminUserDTO) req.getSession().getAttribute(Constant.ADMIN_USER);
        if (admin == null) {
            return new ResultVO(1001);
        }

        return service.insert(oName, extra, iconUrl, downloadType, appUrl, apkUrl, admin.getaId());
    }

    @RequestMapping("delete")
    public int delete(@RequestParam(value = "oId") int oId) {
        return service.delete(oId);
    }
}
