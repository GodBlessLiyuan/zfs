package com.rpa.common.bo;

import com.rpa.common.pojo.PhoneModelPO;
import lombok.Data;

/**
 * @author: xiahui
 * @date: Created in 2020/1/9 19:56
 * @description: TODO
 * @version: 1.0
 */
@Data
public class PhoneModelBO extends PhoneModelPO {
    private String typeEname;
    private String typeName;
    private String typePicture;
}
