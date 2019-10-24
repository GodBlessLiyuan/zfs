package com.rpa.web.dto;

import com.rpa.web.domain.UserDO;
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
    private Integer versionCode;
    private String buildRelease;
    private String manufacturer;
    private String androidModel;

    /**
     * PO 转 DTO
     *
     * @param d d
     * @return DTO
     */
    public static UserDTO convert(UserDO d) {
        UserDTO dto = new UserDTO();

        dto.setPhone(d.getPhone());
        dto.setCreateTime(d.getCreateTime());
        dto.setChanName(d.getChanName());
        dto.setVersionCode(d.getVersionCode());
        dto.setBuildRelease(d.getBuildRelease());
        dto.setManufacturer(d.getManufacturer());
        dto.setAndroidModel(d.getAndroidModel());

        return dto;
    }

    /**
     * PO 批量转 DTO
     *
     * @param dos
     * @return DTOs
     */
    public static List<UserDTO> convert(List<UserDO> dos) {
        List<UserDTO> dtos = new ArrayList<>();

        for (UserDO d : dos) {
            dtos.add(UserDTO.convert(d));
        }

        return dtos;
    }
}
