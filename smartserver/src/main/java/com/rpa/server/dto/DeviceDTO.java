package com.rpa.server.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author: xiahui
 * @date: Created in 2019/10/15 15:36
 * @description: 设备请求参数
 * @version: 1.0
 */
@Data
public class DeviceDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * androidid唯一标识
     */
    private String androidid;
    /**
     * 阿里唯一标识
     */
    private String utdid;
    /**
     * 系统版本号
     */
    private Byte osv;
    /**
     * 系统版本号 Build.VERSION.RELEASE
     */
    private String osre;
    /**
     * 应用渠道
     */
    private String channel;
    /**
     * 手机厂商
     */
    private String factory;
    /**
     * 手机型号
     */
    private String model;
    /**
     * 应用版本号
     */
    private Integer softv;
    /**
     * 手机编译版本号
     */
    private String buildv;
    /**
     * Android端随机生成的字符串
     */
    private String uuid;
    /**
     * 设备imei或meid
     */
    private List<String> imei;
}
