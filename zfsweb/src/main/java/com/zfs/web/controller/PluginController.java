package com.zfs.web.controller;

import com.zfs.common.constant.Constant;
import com.zfs.web.dto.AdminUserDTO;
import com.zfs.web.vo.PluginVO;
import com.zfs.web.service.IPluginService;
import com.zfs.common.vo.ResultVO;
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


    @PostMapping("/plugin/query")
    public ResultVO query(
          @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
          @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
          @RequestParam(value = "username",required = false) String username) {

        Map<String, Object> reqData = new HashMap<>(1);
        reqData.put("username", username);
        return service.query(pageNum, pageSize, reqData);
    }

    @PostMapping("/plugin/queryById")
    public ResultVO queryById(@RequestParam(value = "pluginId") int pluginId) {
        List<PluginVO> dtos = service.queryById(pluginId);
        return new ResultVO<>(1000,dtos.get(0));
    }

    @PostMapping("/plugin/querySoftChannelByIds")
    public ResultVO querySoftChannelByIds(@RequestParam(value = "pluginId") int pluginId, @RequestParam(value =
            "appId") int appId) {
        return new ResultVO(1000,service.querySoftChannelByIds(pluginId, appId));
    }
    /***
     * 这段的数据库应该以为智能助手的为准。
     * */
    @PostMapping("/plugin/insert")
    public ResultVO insert(@RequestParam(value = "file") String file,
                           @RequestParam(value = "appId") int appId,
                           @RequestParam(value = "softChannel") int[] softChannel,
                           @RequestParam(value = "context") String context,
                           @RequestParam(value = "extra") String extra,
                           HttpServletRequest req) {
        // 从Session里获取管理员Id
        AdminUserDTO admin = (AdminUserDTO) req.getSession().getAttribute(Constant.ADMIN_USER);
        if (admin == null) {
            return new ResultVO(1001);
        }

        return service.insert(file, appId, softChannel, context, extra, admin.getaId());
    }

    @PostMapping("/plugin/update")
    public int update(@RequestParam(value = "pluginId") int pluginId,
                      @RequestParam(value = "file", required = false) String file,
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
