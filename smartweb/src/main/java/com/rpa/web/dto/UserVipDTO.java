package com.rpa.web.dto;

import com.rpa.web.domain.UserVipDO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: xiahui
 * @date: Created in 2019/10/9 14:28
 * @description: 用户会员数据
 * @version: 1.0
 */
public class UserVipDTO implements Serializable {

    /**
     * do 转 dto
     * @param d
     * @return
     */
    public static UserVipDTO convert(UserVipDO d) {
        UserVipDTO dto = new UserVipDTO();

        return dto;
    }

    /**
     * dos 转 dtos
     *
     * @param dos
     * @return
     */
    public static List<UserVipDTO> convert(List<UserVipDO> dos) {
        List<UserVipDTO> dtos = new ArrayList<>();

        for (UserVipDO d : dos) {
            dtos.add(UserVipDTO.convert(d));
        }

        return dtos;
    }
}
