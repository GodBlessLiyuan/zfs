package com.zfs.web.controller;

import com.zfs.common.constant.Constant;
import com.zfs.common.vo.ResultVO;
import com.zfs.web.dto.AdminUserDTO;
import com.zfs.web.service.IUserGiftsSercive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/10/3 16:51
 * @description: 新用户送会员
 * @version: 1.0
 */
@RequestMapping("newusergifts")
@RestController
public class UserGiftsController {

    @Autowired
    private IUserGiftsSercive sercive;

    @RequestMapping("query")
    public ResultVO query(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                          @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                          @RequestParam(value = "username", required = false) String username,
                          @RequestParam(value = "comTypeId", required = false) String comTypeId) {
        Map<String, Object> reqData = new HashMap<>(2);
        reqData.put("username", username);
        reqData.put("comTypeId", comTypeId);

        return sercive.query(pageNum, pageSize, reqData);
    }

    @RequestMapping("insert")
    public ResultVO insert(@RequestParam(value = "comTypeId") int comTypeId, HttpServletRequest req) {
        // 从Session里获取管理员Id
        AdminUserDTO admin = (AdminUserDTO) req.getSession().getAttribute(Constant.ADMIN_USER);
        if (admin == null) {
            return new ResultVO(1001);
        }
        return sercive.insert(comTypeId, admin.getaId());
    }

    @RequestMapping("updateStatus")
    public ResultVO updateStatus(@RequestParam(value = "nugId") int nugId,
                                 @RequestParam(value = "status") byte status) {
        return sercive.updateStatus(nugId, status);
    }

    @RequestMapping("delete")
    public ResultVO delete(@RequestParam(value = "nugId") int nugId) {
        return sercive.delete(nugId);
    }
}
