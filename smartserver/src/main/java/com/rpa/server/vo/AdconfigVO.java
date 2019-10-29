package com.rpa.server.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author: dangyi
 * @date: Created in 17:10 2019/10/29
 * @version: 1.0.0
 * @description:
 */
@Data
public class AdconfigVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String strategy;
    private List<Map<String, Object>> ads;
}
