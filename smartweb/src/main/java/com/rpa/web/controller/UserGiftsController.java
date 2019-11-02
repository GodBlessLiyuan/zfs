package com.rpa.web.controller;

import com.rpa.web.common.Constant;
import com.rpa.web.dto.AdminUserDTO;
import com.rpa.web.dto.UserGiftsDTO;
import com.rpa.web.enumeration.ExceptionEnum;
import com.rpa.web.exception.PromptException;
import com.rpa.web.service.IUserGiftsSercive;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.web.vo.ResultVO;
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
    public DTPageInfo<UserGiftsDTO> query(@RequestParam(value = "draw", defaultValue = "1") int draw,
                                          @RequestParam(value = "start", defaultValue = "1") int pageNum,
                                          @RequestParam(value = "length", defaultValue = "10") int pageSize,
                                          @RequestParam(value = "username") String username,
                                          @RequestParam(value = "comTypeId") String comTypeId) {
        Map<String, Object> reqData = new HashMap<>(2);
        reqData.put("username", username);
        reqData.put("comTypeId", comTypeId);

        return sercive.query(draw, pageNum, pageSize, reqData);
    }

    @RequestMapping("insert")
    public ResultVO insert(@RequestParam(value = "comTypeId") int comTypeId, HttpServletRequest req) {
        // 从Session里获取管理员Id
        AdminUserDTO admin = (AdminUserDTO) req.getSession().getAttribute(Constant.ADMIN_USER);
        if (admin == null) {
            throw new PromptException(ExceptionEnum.SESSION_ERROR);
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
