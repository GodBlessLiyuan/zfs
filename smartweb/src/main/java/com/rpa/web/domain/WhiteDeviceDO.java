package com.rpa.web.domain;

import com.rpa.web.pojo.WhiteDevicePO;
import lombok.Data;

/**
 * @author: xiahui
 * @date: Created in 2019/10/23 16:50
 * @description: 测试白名单
 * @version: 1.0
 */
@Data
public class WhiteDeviceDO extends WhiteDevicePO {

    private String imei;
}
