package com.rpa.server.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rpa.common.dto.TokenDTO;
import com.rpa.common.pojo.UserPO;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author: velve
 * @date: Created in 2020/5/27 16:24
 * @description: TODO
 * @version: 1.0
 */
@Data
public class UserDouDTO extends TokenDTO {

    private Long userId;

    private String username;

    private String phone;

    private String ip;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private String chanName;

    private Integer softChannelId;

    private static final long serialVersionUID = 1L;

    public static void convertDTO(UserDouDTO userDTO, UserPO userPO) {
        userDTO.setChanName(userPO.getChanName());
        userDTO.setCreateTime(userPO.getCreateTime());
        userDTO.setUpdateTime(userPO.getUpdateTime());
        userDTO.setPhone(userPO.getPhone());
//        userDTO.setSoftChannelId(userPO.getSoftChannelId());
        userDTO.setUsername(userPO.getUsername());
        userDTO.setIp(userPO.getIp());
    }
}
