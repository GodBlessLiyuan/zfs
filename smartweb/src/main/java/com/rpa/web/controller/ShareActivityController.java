package com.rpa.web.controller;

import com.rpa.web.vo.ShareActivityVO;
import com.rpa.web.service.ShareActivityService;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.common.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

/**
 * @author: dangyi
 * @date: Created in 10:13 2019/10/10
 * @version: 1.0.0
 * @description:
 */
@RestController
@RequestMapping("shareactivity")
public class ShareActivityController {

    @Autowired
    private ShareActivityService shareActivityService;

    /**
     * 查询
     * @param draw
     * @param start
     * @param length
     * @param type
     * @return
     */
    @GetMapping("query")
    public DTPageInfo<ShareActivityVO> query(@RequestParam(value = "draw", defaultValue = "1") int draw,
                                             @RequestParam(value = "start", defaultValue = "1") int start,
                                             @RequestParam(value = "length", defaultValue = "10") int length,
                                             @RequestParam(value = "type", required = false) Byte type
    ) {

        // 调用业务层，返回页面结果
        DTPageInfo<ShareActivityVO> dTPageInfo = shareActivityService.query(draw, start, length, type);
        return dTPageInfo;
    }


    /**
     * 查询：根据ID查询数据
     * @param materialId
     * @return
     */
    @PostMapping("queryById")
    public ResultVO queryById(@RequestParam(value = "materialId")Integer materialId) {
        return this.shareActivityService.queryById(materialId);
    }


    /**
     * 插入
     * @param type
     * @param contentText
     * @param contentImage
     * @param extra
     * @param httpSession
     * @return
     */
    @PostMapping("insert")
    public ResultVO insert(@RequestParam(value = "type")Byte type,
                      @RequestParam(value = "contentText", required = false)String contentText,
                      @RequestParam(value = "contentImage", required = false) MultipartFile contentImage,
                      @RequestParam(value = "extra", required = false) String extra,
                      HttpSession httpSession) {
        return this.shareActivityService.insert(type, contentText, contentImage, extra, httpSession);
    }

    /**
     * 修改
     * @param type
     * @param contentText
     * @param contentImage
     * @param extra
     * @param httpSession
     * @return
     */
    @PostMapping("update")
    public ResultVO update(@RequestParam(value = "materialId")Integer materialId,
                           @RequestParam(value = "type")Byte type,
                           @RequestParam(value = "contentText", required = false)String contentText,
                           @RequestParam(value = "contentImage", required = false) MultipartFile contentImage,
                           @RequestParam(value = "extra", required = false) String extra,
                           HttpSession httpSession){
        return this.shareActivityService.update(materialId, type, contentText, contentImage, extra, httpSession);
    }


    /**
     * 删除
     * @param materialId
     * @return
     */
    @PostMapping("delete")
    public ResultVO update(int materialId){
        return this.shareActivityService.delete(materialId);
    }
}
