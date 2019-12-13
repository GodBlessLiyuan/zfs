package com.rpa.web.vo;

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
public class UserVO implements Serializable {

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

    public static UserVO convert(UserBO bo) {
        UserVO vo = new UserVO();

        vo.setPhone(bo.getPhone());
        vo.setCreateTime(bo.getCreateTime());
        vo.setChanName(bo.getChanName());
        vo.setVersionName(bo.getVersionName());
        vo.setBuildRelease(bo.getBuildRelease());
        vo.setManufacturer(bo.getManufacturer());
        vo.setAndroidModel(bo.getAndroidModel());

        return vo;
    }

    public static List<UserVO> convert(List<UserBO> bos) {
        List<UserVO> vos = new ArrayList<>();

        for (UserBO bo : bos) {
            vos.add(UserVO.convert(bo));
        }

        return vos;
    }
}
