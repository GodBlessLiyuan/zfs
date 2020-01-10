package com.rpa.server.dto;

import com.rpa.common.pojo.PhoneModelPO;
import com.rpa.common.pojo.PhoneTypePO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author: xiahui
 * @date: Created in 2020/1/10 8:46
 * @description: TODO
 * @version: 1.0
 */
@Data
public class PhoneModelDTO implements Serializable {
    private static final long SerialVersionID = 1L;

    private PhoneTypePO phoneType;
    private List<PhoneModelPO> phoneModels;
}
