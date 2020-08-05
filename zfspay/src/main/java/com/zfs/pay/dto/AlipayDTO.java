package com.zfs.pay.dto;

import com.zfs.pay.dto.base.TokenDTO;
import lombok.Data;

/**
 * @author: dangyi
 * @date: Created in 16:26 2019/11/7
 * @version: 1.0.0
 * @description:
 */
@Data
public class AlipayDTO extends TokenDTO {
    private Integer cmdyid;
    private String number;
    private String verify;
    private String um;
}
