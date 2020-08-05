package com.rpa.web.controller;

import com.rpa.web.dto.AdminUserDTO;
import com.rpa.web.service.AdminUserService;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.common.vo.ResultVO;
import com.rpa.web.vo.AdminUserVO;
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
     * @param start
     * @param length
     * @param phone
     * @param extra
     * @return
     */
    @GetMapping("query")
    public DTPageInfo<AdminUserVO> query(@RequestParam(value = "draw", defaultValue = "1") int draw,
                                         @RequestParam(value = "start", defaultValue = "1") int start,
                                         @RequestParam(value = "length", defaultValue = "10") int length,
                                         @RequestParam(value = "phone", required = false) String phone,
                                         @RequestParam(value = "extra", required = false) String extra
    ) {

        // 调用业务层，返回页面结果
        DTPageInfo<AdminUserVO> dTPageInfo = adminUserService.query(draw, start, length, phone, extra);
        return dTPageInfo;
    }


    /**
     * 查询所有角色
     * @return
     */
    @GetMapping("queryAllRoles")
    public ResultVO queryAllRoles() {
        return this.adminUserService.queryAllRoles();
    }


    /**
     * 查询
     * @param aId
     * @return
     */
    @GetMapping("queryById")
    public ResultVO queryById(@RequestParam(value = "aId")Integer aId) {
        return this.adminUserService.queryById(aId);
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
    public ResultVO delete(@RequestParam(value = "aId")Integer aId) {
        return this.adminUserService.delete(aId);
    }
}
