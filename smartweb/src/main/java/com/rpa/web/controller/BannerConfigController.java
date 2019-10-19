package com.rpa.web.controller;

import com.rpa.web.dto.BannerConfigDTO;
import com.rpa.web.service.BannerConfigService;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.web.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * @param pageNum
     * @param pageSize
     * @param name
     * @param status
     * @return
     */
    @GetMapping("query")
    public DTPageInfo<BannerConfigDTO> query(@RequestParam(value = "draw", defaultValue = "1") int draw,
                                             @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                             @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                             @RequestParam(value = "name", required = false) String name,
                                             @RequestParam(value = "status", required = false) Byte status
    ) {

        // 调用业务层，返回页面结果
        DTPageInfo<BannerConfigDTO> dTPageInfo = bannerConfigService.query(draw, pageNum, pageSize, name, status);
        return dTPageInfo;
    }

    /**
     * 插入
     * @param bannerConfigDTO
     * @param httpSession
     * @return
     */
    @PostMapping("insert")
    public ResultVO insert(BannerConfigDTO bannerConfigDTO, HttpSession httpSession) {
        return this.bannerConfigService.insert(bannerConfigDTO, httpSession);
    }

    /**
     * 修改状态
     * @param bannerConfigDTO
     * @param httpSession
     * @return
     */
    @PostMapping("/update/status")
    public ResultVO update(BannerConfigDTO bannerConfigDTO, HttpSession httpSession) {
        return this.bannerConfigService.update(bannerConfigDTO, httpSession);
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
