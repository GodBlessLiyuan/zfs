package com.zfs.web.dto;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;

/**
 * @Description:
 * @author: liyuan
 * @data 2020-08-27 16:44
 */
@Data
public class FunctionVideoDTO implements Serializable {
    private Integer functionId;
    private String funName;
    private String[] urls;
    private String extra;
}
