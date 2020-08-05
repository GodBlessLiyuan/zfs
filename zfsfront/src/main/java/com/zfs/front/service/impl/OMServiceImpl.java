package com.zfs.front.service.impl;

import com.zfs.common.mapper.AppMapper;
import com.zfs.common.pojo.AppPO;
import com.zfs.front.service.IOMService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author: xiahui
 * @date: Created in 2020/5/13 9:40
 * @description: 运营需求
 * @version: 1.0
 */
@Service
public class OMServiceImpl implements IOMService {
    @Autowired
    private AppMapper appMapper;
    @Value("${file.rootDir}")
    private String rootDir;

    @Override
    public String findMaxVersionAPP(HttpServletResponse res) throws IOException {
        AppPO po = appMapper.queryMaxByVerId(0, 2, 2);
        if (null == po) {
            return null;
        }

        res.addHeader("Content-Disposition", "attachment;fileName=vbooster.apk");
        InputStream is = new FileInputStream(rootDir + po.getUrl());
        res.getOutputStream().write(IOUtils.toByteArray(is));
        return "下载成功";
    }
}
