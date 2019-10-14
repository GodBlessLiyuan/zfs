package com.rpa.web.controller;

import com.rpa.web.dto.AdminUserDTO;
import com.rpa.web.service.AdminUserService;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.web.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("admin")
public class AdminUserController {

    @Autowired
    private AdminUserService adminUserService;

    /**
     * 账号管理——查询
     * @param draw
     * @param pageNum
     * @param pageSize
     * @param phone
     * @param extra
     * @return
     */
    @GetMapping("query")
    public DTPageInfo<AdminUserDTO> query(@RequestParam(value = "draw", defaultValue = "1") int draw,
                                          @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                          @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                          @RequestParam(value = "phone", required = false) String phone,
                                          @RequestParam(value = "extra", required = false) Byte extra
    ) {

        // 调用业务层，返回页面结果
        DTPageInfo<AdminUserDTO> dTPageInfo = adminUserService.query(draw, pageNum, pageSize, phone, extra);
        return dTPageInfo;
    }

    /**
     * 账号管理——插入
     * @param
     * @return
     */
    @PostMapping("insert")
    public ResultVO insert(AdminUserDTO adminUserDTO, HttpSession httpSession) {
        return this.adminUserService.insert(adminUserDTO, httpSession);
    }

    /**
     * 账号管理——修改
     * @param
     * @return
     */
    @PostMapping("update")
    public ResultVO update(AdminUserDTO adminUserDTO, HttpSession httpSession) {
        return this.adminUserService.update(adminUserDTO, httpSession);
    }

    /**
     * 账号管理——删除
     * @param
     * @return
     */
    @PostMapping("delete")
    public ResultVO delete(int aId) {
        return this.adminUserService.delete(aId);
    }
}
