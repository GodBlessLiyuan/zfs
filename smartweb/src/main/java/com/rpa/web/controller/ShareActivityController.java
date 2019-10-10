package com.rpa.web.controller;

import com.rpa.web.dto.ShareActivityDTO;
import com.rpa.web.service.ShareActivityService;
import com.rpa.web.utils.DTPageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author: dangyi
 * @date: Created in 10:13 2019/10/10
 * @version: 1.0.0
 * @description:
 */
@RestController
@RequestMapping("shareActivity")
public class ShareActivityController {

    @Autowired
    private ShareActivityService shareActivityService;

    /**
     * 查询
     * @param draw
     * @param pageNum
     * @param pageSize
     * @param type
     * @return
     */
    @GetMapping("query")
    public DTPageInfo<ShareActivityDTO> query(@RequestParam(value = "draw", defaultValue = "1") int draw,
                                              @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                              @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                              @RequestParam(value = "type", required = false) int type
    ) {

        // 调用业务层，返回页面结果
        DTPageInfo<ShareActivityDTO> dTPageInfo = shareActivityService.query(draw, pageNum, pageSize, type);
        return dTPageInfo;
    }

    /**
     * 插入
     * @param shareActivityDTO
     * @param httpSession
     * @return
     */
    @PostMapping("insert")
    public int insert(ShareActivityDTO shareActivityDTO, HttpSession httpSession) {
        return this.shareActivityService.insert(shareActivityDTO, httpSession);
    }

    /**
     * 修改
     * @param shareActivityDTO
     * @param httpSession
     * @return
     */
    @PostMapping("update")
    public int update(ShareActivityDTO shareActivityDTO, HttpSession httpSession){
        return this.shareActivityService.update(shareActivityDTO, httpSession);
    }

    /**
     * 删除
     * @param materialId
     * @return
     */
    @PostMapping("delete")
    public int update(int materialId){
        return this.shareActivityService.delete(materialId);
    }
}
