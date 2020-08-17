package com.zfsweb.service;

import com.zfs.common.mapper.BatchInfoMapper;
import com.zfs.common.mapper.ChBatchMapper;
import com.zfs.web.WebApplication;
import com.zfs.web.service.impl.ChBatchScheduleServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description: 主要用于测试定时任务类
 * @author: liyuan
 * @data 2020-08-15 11:02
 * WebApplication：应该是应用容器，包含了web模块的所有对象
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {WebApplication.class})
public class BactchInfoServiceTest {
    @Autowired
    private ChBatchScheduleServiceImpl service;
    @Test
    public void schudule(){
        service.overLineCount();
    }
}
