package com.rpa.web.controller;

import com.rpa.web.dto.UserGiftsDTO;
import com.rpa.web.service.IUserGiftsSercive;
import com.rpa.web.utils.DTPageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public int insert(@RequestParam(value = "comTypeId") int comTypeId) {
        return sercive.insert(comTypeId);
    }

    @RequestMapping("updateStatus")
    public int updateStatus(@RequestParam(value = "nugId") int nugId,
                            @RequestParam(value = "status") byte status) {
        return sercive.updateStatus(nugId, status);
    }

    @RequestMapping("delete")
    public int delete(@RequestParam(value = "nugId") int nugId) {
        return sercive.delete(nugId);
    }
}
