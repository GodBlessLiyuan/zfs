package com.zfs.web.controller;

import com.zfs.common.constant.Constant;
import com.zfs.web.dto.AdminUserDTO;
import com.zfs.web.dto.AppVersionDTO;
import com.zfs.web.service.IAppService;
import com.zfs.common.vo.ResultVO;
import com.zfs.web.vo.AppVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/10/1 13:48
 * @description: 版本更新
 * @version: 1.0
 */
@RequestMapping("appversion")
@RestController
public class AppController {

    @Autowired
    private IAppService service;

    @PostMapping("query")
    public ResultVO query(
           @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
           @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
           @RequestParam(value = "updateType",required = false) Byte updateType) {

        Map<String, Object> reqData = new HashMap<>(1);
        reqData.put("updateType", updateType);

        return service.query( pageNum, pageSize, reqData);
    }

    @PostMapping("queryAll")
    public ResultVO queryAll() {
        List<AppVO> datas = service.queryAll();
        return new ResultVO(1000,datas);
    }

    @PostMapping("queryById")
    public ResultVO queryById(@RequestParam(value = "appId") int appId) {
        AppVO appVO = service.queryById(appId);
        return new ResultVO(1000,appVO);
    }

    @PostMapping("insert")
    public ResultVO insert(@RequestBody AppVersionDTO appVersionDTO, HttpServletRequest req) {
        AdminUserDTO admin = (AdminUserDTO) req.getSession().getAttribute(Constant.ADMIN_USER);
        if (admin == null) {
            return new ResultVO(1001);
        }
        return service.insert(appVersionDTO.getProjectName(),appVersionDTO.getUpdateType(),appVersionDTO.getSoftChannel(),appVersionDTO.getContext()
                ,appVersionDTO.getExtra(), admin.getaId());
    }

    @PostMapping("update")
    public ResultVO update(@RequestBody AppVersionDTO appVersionDTO) {
        return service.update(appVersionDTO.getAppId(), appVersionDTO.getProjectName(),appVersionDTO.getUpdateType(),appVersionDTO.getSoftChannel(),
                appVersionDTO.getContext(), appVersionDTO.getExtra());
    }

    @PostMapping("updateStatus")
    public ResultVO updateStatus(@RequestParam(value = "appId") int appId,
                            @RequestParam(value = "status") int status) {
        return service.updateStatus(appId, status);
    }

    @PostMapping("delete")
    public ResultVO delete(@RequestParam(value = "appId") int appId) {
        return service.delete(appId);
    }
}
