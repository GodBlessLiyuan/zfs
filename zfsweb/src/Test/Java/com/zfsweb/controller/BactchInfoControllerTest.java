package com.zfsweb.controller;

import com.zfs.common.vo.ResultVO;
import com.zfs.web.WebApplication;
import com.zfsweb.BaseTest;
import org.apache.poi.hssf.record.formula.functions.T;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @Description:
 * @author: liyuan
 * @data 2020-08-15 11:06
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
@AutoConfigureMockMvc
public class BactchInfoControllerTest extends BaseTest {
    @Test
    public void testSchule() throws Exception {
        ResultVO<T> resultVO = super.mockMvcPostUrl("/chBatchScheduleTest");
        assertNotNull(resultVO);
        assertEquals(Integer.valueOf(1000), resultVO.getStatus());
    }
}
