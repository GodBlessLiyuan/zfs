package com.rpa.web.dto;

import com.rpa.common.pojo.ViptypePO;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @author: liyuan
 * @data 2020-05-20 19:43
 */
@Data
public class BatchSycInfoDTO implements Serializable {
    private Integer day;

    private String phone;

    /***
     * 可能需要将用户的一些信息接收和传输，
     * */
    private String key;
    private UserDouDTO userDouDTO;
}
