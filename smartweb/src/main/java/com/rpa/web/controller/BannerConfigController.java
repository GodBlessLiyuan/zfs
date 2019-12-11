package com.rpa.web.controller;

import com.rpa.web.service.BannerConfigService;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.common.vo.ResultVO;
import com.rpa.web.vo.BannerConfigVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

/**
 * @author: dangyi
 * @date: Created in 18:43 2019/10/10
 * @version: 1.0.0
 * @description:
 */
@RestController
@RequestMapping("bannerconfig")
public class BannerConfigController {

    @Autowired
    private BannerConfigService bannerConfigService;

    /**
     * 查询
     * @param draw
     * @param start
     * @param length
     * @param name
     * @param status
     * @return
     */
    @GetMapping("query")
    public DTPageInfo<BannerConfigVO> query(@RequestParam(value = "draw", defaultValue = "1") int draw,
                                            @RequestParam(value = "start", defaultValue = "1") int start,
                                            @RequestParam(value = "length", defaultValue = "10") int length,
                                            @RequestParam(value = "name", required = false) String name,
                                            @RequestParam(value = "status", required = false) Byte status
    ) {

        // 调用业务层，返回页面结果
        DTPageInfo<BannerConfigVO> dTPageInfo = bannerConfigService.query(draw, start, length, name, status);
        return dTPageInfo;
    }

    /**
     * 插入
     * @param name
     * @param picPath
     * @param url
     * @param httpSession
     * @return
     */
    @PostMapping("insert")
    public ResultVO insert(@RequestParam(value = "name") String name,
                           @RequestParam(value = "picPath") MultipartFile picPath,
                           @RequestParam(value = "url") String url,
                           HttpSession httpSession) {
        return this.bannerConfigService.insert(name, picPath, url, httpSession);
    }

    /**
     * 修改状态
     * @param bannerId
     * @param status
     * @param httpSession
     * @return
     */
    @PostMapping("/update/status")
    public ResultVO update(@RequestParam(value = "bannerId")Integer bannerId,
                           @RequestParam(value = "status")Byte status,
                           HttpSession httpSession) {
        return this.bannerConfigService.update(bannerId, status, httpSession);
    }

    /**
     * 删除
     * @param bannerId
     * @return
     */
    @PostMapping("delete")
    public ResultVO delete(@RequestParam(value = "bannerId")Integer bannerId) {
        return this.bannerConfigService.delete(bannerId);
    }

}
