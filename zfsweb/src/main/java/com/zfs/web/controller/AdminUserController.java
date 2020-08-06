package com.zfs.web.controller;

import com.zfs.web.dto.AdminUserDTO;
import com.zfs.web.service.AdminUserService;
import com.zfs.common.vo.ResultVO;
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
     * @param pageNum
     * @param pageSize
     * @param phone
     * @param extra
     * @return
     */
    @PostMapping("query")
    public ResultVO query(
         @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
         @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
         @RequestParam(value = "phone", required = false) String phone,
         @RequestParam(value = "extra", required = false) String extra
    ) {

        // 调用业务层，返回页面结果
        return adminUserService.query(pageNum, pageSize, phone, extra);
    }


    /**
     * 查询所有角色
     * @return
     */
    @PostMapping("queryAllRoles")
    public ResultVO queryAllRoles() {
        return this.adminUserService.queryAllRoles();
    }


    /**
     * 查询
     * @param aId
     * @return
     */
    @PostMapping("queryById")
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
