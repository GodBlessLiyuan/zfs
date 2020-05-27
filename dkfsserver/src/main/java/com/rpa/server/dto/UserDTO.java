package com.rpa.server.dto;

import com.rpa.common.dto.TokenDTO;
import lombok.Data;

import java.util.Date;

/**
 * @author: velve
 * @date: Created in 2020/5/27 16:24
 * @description: TODO
 * @version: 1.0
 */
@Data
public class UserDTO extends TokenDTO {

    private Long userId;

    private String username;

    private String phone;

    private String ip;

    private Date createTime;

    private Date updateTime;

    private String chanName;

    private Integer softChannelId;

    private static final long serialVersionUID = 1L;

}
