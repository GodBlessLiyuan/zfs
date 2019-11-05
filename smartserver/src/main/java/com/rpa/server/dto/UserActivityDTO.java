package com.rpa.server.dto;

import com.rpa.server.dto.base.TokenDTO;
import lombok.Data;

/**
 * @author: xiahui
 * @date: Created in 2019/11/5 18:55
 * @description: 用户活动
 * @version: 1.0
 */
@Data
public class UserActivityDTO extends TokenDTO {

    private String picdata1;
}
