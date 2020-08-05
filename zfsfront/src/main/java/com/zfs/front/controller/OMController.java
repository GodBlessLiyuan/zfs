package com.zfs.front.controller;

import com.zfs.front.service.IOMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: xiahui
 * @date: Created in 2020/5/13 9:37
 * @description: 运营需求
 * @version: 1.0
 */
@RequestMapping("om")
@Controller
public class OMController {
    @Autowired
    private IOMService iomService;

    /**
     * 查找 官网 最新 发布 APP 包
     *
     * @return
     */
    @RequestMapping(value = "findLatest", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String find(HttpServletResponse res) throws IOException {
        return iomService.findMaxVersionAPP(res);
    }
}
