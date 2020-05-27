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
    /**
     * 用户唯一标识id
     */
    private Long ud;
    private UserDouDTO userDTO;
    private ViptypePO vipTypePO;

}
