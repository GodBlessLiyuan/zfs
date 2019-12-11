package com.rpa.web.controller;

import com.rpa.common.constant.Constant;
import com.rpa.common.dto.AdminUserDTO;
import com.rpa.web.dto.AppDTO;
import com.rpa.web.service.IAppService;
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
 * @date: Created in 2019/10/1 13:48
 * @description: 版本更新
 * @version: 1.0
 */
@RequestMapping("appversion")
@RestController
public class AppController {

    @Autowired
    private IAppService service;

    @RequestMapping("query")
    public DTPageInfo<AppDTO> query(@RequestParam(value = "draw", defaultValue = "1") int draw,
                                    @RequestParam(value = "start", defaultValue = "1") int pageNum,
                                    @RequestParam(value = "length", defaultValue = "10") int pageSize,
                                    @RequestParam(value = "updateType") byte updateType) {

        Map<String, Object> reqData = new HashMap<>(1);
        reqData.put("updateType", updateType);

        return service.query(draw, pageNum, pageSize, reqData);
    }

    @RequestMapping("queryAll")
    public List<AppDTO> queryAll() {
        List<AppDTO> datas = service.queryAll();
        return datas;
    }

    @RequestMapping("queryById")
    public AppDTO queryById(@RequestParam(value = "appId") int appId) {
        List<AppDTO> dtos = service.queryById(appId);
        return dtos.get(0);
    }

    @PostMapping("insert")
    public ResultVO insert(@RequestParam(value = "file") MultipartFile file,
                           @RequestParam(value = "updateType") byte updateType,
                           @RequestParam(value = "softChannel") int[] softChannel,
                           @RequestParam(value = "context") String context,
                           @RequestParam(value = "extra") String extra, HttpServletRequest req) {
        AdminUserDTO admin = (AdminUserDTO) req.getSession().getAttribute(Constant.ADMIN_USER);
        if (admin == null) {
            return new ResultVO(1001);
        }

        return service.insert(file, updateType, softChannel, context, extra, admin.getaId());
    }

    @PostMapping("update")
    public int update(@RequestParam(value = "appId") int appId,
                      @RequestParam(value = "file", required = false) MultipartFile file,
                      @RequestParam(value = "updateType") byte updateType,
                      @RequestParam(value = "softChannel") int[] softChannel,
                      @RequestParam(value = "context") String context,
                      @RequestParam(value = "extra") String extra) {
        return service.update(appId, file, updateType, softChannel, context, extra);
    }

    @RequestMapping("updateStatus")
    public int updateStatus(@RequestParam(value = "appId") int appId,
                            @RequestParam(value = "status") int status) {
        return service.updateStatus(appId, status);
    }

    @RequestMapping("delete")
    public int delete(@RequestParam(value = "appId") int appId) {
        return service.delete(appId);
    }
}
