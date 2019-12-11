package com.rpa.web.dto;

import com.rpa.common.bo.UserBO;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: xiahui
 * @date: Created in 2019/9/29 16:16
 * @description: 用户信息
 * @version: 1.0
 */
@Data
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long userId;
    private String username;
    private String phone;
    private Date createTime;

    private String chanName;
    private String versionName;
    private String buildRelease;
    private String manufacturer;
    private String androidModel;

    public static UserDTO convert(UserBO bo) {
        UserDTO dto = new UserDTO();

        dto.setPhone(bo.getPhone());
        dto.setCreateTime(bo.getCreateTime());
        dto.setChanName(bo.getChanName());
        dto.setVersionName(bo.getVersionName());
        dto.setBuildRelease(bo.getBuildRelease());
        dto.setManufacturer(bo.getManufacturer());
        dto.setAndroidModel(bo.getAndroidModel());

        return dto;
    }

    public static List<UserDTO> convert(List<UserBO> bos) {
        List<UserDTO> dtos = new ArrayList<>();

        for (UserBO bo : bos) {
            dtos.add(UserDTO.convert(bo));
        }

        return dtos;
    }
}
