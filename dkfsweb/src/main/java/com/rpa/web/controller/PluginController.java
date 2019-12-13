package com.rpa.web.controller;

import com.rpa.common.constant.Constant;
import com.rpa.web.dto.AdminUserDTO;
import com.rpa.web.vo.PluginVO;
import com.rpa.web.service.IPluginService;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.common.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/10/6 20:09
 * @description: 插件更新
 * @version: 1.0
 */
@RestController
public class PluginController {

    @Autowired
    private IPluginService service;


    @RequestMapping("/plugin/query")
    public DTPageInfo<PluginVO> query(@RequestParam(value = "draw", defaultValue = "1") int draw,
                                      @RequestParam(value = "start", defaultValue = "1") int pageNum,
                                      @RequestParam(value = "length", defaultValue = "10") int pageSize,
                                      @RequestParam(value = "username") String username) {

        Map<String, Object> reqData = new HashMap<>(1);
        reqData.put("username", username);

        return service.query(draw, pageNum, pageSize, reqData);
    }

    @RequestMapping("/plugin/queryById")
    public PluginVO queryById(@RequestParam(value = "pluginId") int pluginId) {
        List<PluginVO> dtos = service.queryById(pluginId);
        return dtos.get(0);
    }

    @RequestMapping("/plugin/querySoftChannelByIds")
    public List<Integer> querySoftChannelByIds(@RequestParam(value = "pluginId") int pluginId, @RequestParam(value =
            "appId") int appId) {
        return service.querySoftChannelByIds(pluginId, appId);
    }

    @PostMapping("/plugin/insert")
    public ResultVO insert(@RequestParam(value = "file") MultipartFile file,
                           @RequestParam(value = "appId") int appId,
                           @RequestParam(value = "softChannel") int[] softChannel,
                           @RequestParam(value = "context") String context,
                           @RequestParam(value = "extra") String extra, HttpServletRequest req) {
        // 从Session里获取管理员Id
        AdminUserDTO admin = (AdminUserDTO) req.getSession().getAttribute(Constant.ADMIN_USER);
        if (admin == null) {
            return new ResultVO(1001);
        }

        return service.insert(file, appId, softChannel, context, extra, admin.getaId());
    }

    @PostMapping("/plugin/update")
    public int update(@RequestParam(value = "pluginId") int pluginId,
                      @RequestParam(value = "file", required = false) MultipartFile file,
                      @RequestParam(value = "appId") int appId,
                      @RequestParam(value = "softChannel") int[] softChannel,
                      @RequestParam(value = "context") String context,
                      @RequestParam(value = "extra") String extra) {
        return service.update(pluginId, file, appId, softChannel, context, extra);
    }

    @RequestMapping("/plugin/updateStatus")
    public int updateStatus(@RequestParam(value = "pluginId") int pluginId,
                            @RequestParam(value = "status") byte status) {
        return service.updateStatus(pluginId, status);
    }

    @RequestMapping("/plugin/delete")
    public int delete(@RequestParam(value = "pluginId") int pluginId) {
        return service.delete(pluginId);
    }
}
