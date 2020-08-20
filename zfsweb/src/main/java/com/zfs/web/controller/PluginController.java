package com.zfs.web.controller;

import com.zfs.common.constant.Constant;
import com.zfs.web.dto.AdminUserDTO;
import com.zfs.web.dto.PluginDTO;
import com.zfs.web.vo.Plugin2VO;
import com.zfs.web.vo.PluginVO;
import com.zfs.web.service.IPluginService;
import com.zfs.common.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
          @RequestParam(value = "type",required = false) Byte type) {

        Map<String, Object> reqData = new HashMap<>(1);
        reqData.put("type", type);
        return service.query(pageNum, pageSize, reqData);
    }

    @PostMapping("/plugin/queryById")
    public ResultVO queryById(@RequestParam(value = "pluginId") int pluginId) {
        List<Plugin2VO> dtos = service.queryById(pluginId);
        return new ResultVO<>(1000,dtos.get(0));
    }
    @Deprecated
    @PostMapping("/plugin/querySoftChannelByIds")
    public ResultVO querySoftChannelByIds(@RequestParam(value = "pluginId") int pluginId, @RequestParam(value =
            "appId") int appId) {
        return new ResultVO(1000,service.querySoftChannelByIds(pluginId, appId));
    }
    /***
     * 版本信息
     * */
    @RequestMapping("/plugin/queryAppVersion")
    public ResultVO queryAppVersion(){
        return new ResultVO(1000,service.queryAppVersion());
    }
    /***
     * 这段的数据库应该以为智能助手的为准。
     * */
    @PostMapping("/plugin/insert")
    public ResultVO insert(@RequestBody PluginDTO pluginDTO,HttpServletRequest req) {
        // 从Session里获取管理员Id
        AdminUserDTO admin = (AdminUserDTO) req.getSession().getAttribute(Constant.ADMIN_USER);
        if (admin == null) {
            return new ResultVO(1001);
        }

        return service.insert(pluginDTO.getFile(), pluginDTO.getType(),pluginDTO.getAppIdS(),pluginDTO.getSoftChannelS(),
                pluginDTO.getContext(),pluginDTO.getExtra(), admin.getaId());
    }

    @PostMapping("/plugin/update")
    public ResultVO update(@RequestBody PluginDTO dto) {
        service.update(dto.getPluginId(),dto.getFile(),dto.getAppIdS(),dto.getSoftChannelS(),dto.getContext(),dto.getExtra(),dto.getType());
        return new ResultVO(1000);
    }

    @RequestMapping("/plugin/updateStatus")
    public ResultVO updateStatus(@RequestParam(value = "pluginId") int pluginId,
                            @RequestParam(value = "status") byte status) {
        return new ResultVO(1000,service.updateStatus(pluginId, status));
    }

    @RequestMapping("/plugin/delete")
    public ResultVO delete(@RequestParam(value = "pluginId") int pluginId) {
        return new ResultVO(1000,service.delete(pluginId));
    }
}
