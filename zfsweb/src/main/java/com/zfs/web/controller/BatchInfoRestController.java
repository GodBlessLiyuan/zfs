package com.zfs.web.controller;

import com.zfs.common.vo.ResultVO;
import com.zfs.web.bo.UserToBO;
import com.zfs.web.dto.BatchSycInfoDTO;
import com.zfs.web.service.IBatchInfoRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @author: liyuan
 * @data 2020-05-21 12:36
 */
@RequestMapping("v1.0")
@RestController
public class BatchInfoRestController {
    @Autowired
    private IBatchInfoRestService service;
    /**
     * 向外暴露接口
     * */
    @PostMapping("keySycActivate")
    public ResultVO keySycActivate(@RequestBody BatchSycInfoDTO dto, HttpServletRequest req) {
        return service.activateSync(dto);
    }

    /***
     * 向外暴露接口
     * */
    @PostMapping("keyactivate")
    public ResultVO keyActivateZnzj(@RequestBody BatchSycInfoDTO dto, HttpServletRequest req) {
        return service.keyActivateZnzj(dto);
    }

    @PostMapping("buy_zj_douOrder")
    public ResultVO bugZJDouOrder(@RequestBody UserToBO dto, HttpServletRequest req) {
        return service.bugZJDouOrder(dto);
    }

}
