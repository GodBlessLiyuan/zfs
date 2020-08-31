package com.zfsweb.service;

import com.zfs.web.WebApplication;
import com.zfs.web.utils.FileUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

/**
 * @Description:
 * @author: liyuan
 * @data 2020-08-31 11:40
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {WebApplication.class})
public class AppResolve {
    @Test
    public void testJiexi() {
        String path="frameworkapp-debug.apk";
        Map<String, Object> apkInfo = FileUtil.resolveApk(path);
        System.out.println(apkInfo);
    }
}
