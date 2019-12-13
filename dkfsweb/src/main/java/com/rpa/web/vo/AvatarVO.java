package com.rpa.web.vo;

import com.rpa.common.bo.AvatarBO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author: xiahui
 * @date: Created in 2019/12/12 20:19
 * @description: 分身更新
 * @version: 1.0
 */
@Data
public class AvatarVO implements Serializable {
    private static final long serialVersionUID = 1L;

    public static List<AvatarVO> convert(List<AvatarBO> bos) {
        return null;
    }
}
