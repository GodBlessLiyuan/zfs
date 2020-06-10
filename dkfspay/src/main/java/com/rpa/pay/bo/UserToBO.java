package com.rpa.pay.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @author: liyuan
 * @data 2020-06-10 15:43
 */
@Data
public class UserToBO implements Serializable {
    private String username;
    private String phone;
    private String ip;
    private String chanName;

    private Byte type;
    private int day;
    private String cmdyName;
    private String comTypeName;
    private String comName;
}
