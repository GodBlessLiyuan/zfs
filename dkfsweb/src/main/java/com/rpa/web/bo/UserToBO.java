package com.rpa.web.bo;

import com.rpa.common.pojo.UserPO;
import lombok.Data;

/**
 * @Description: 助手发送给多开分身的对象，该对象内容是天数和UserPO对象
 * @author: liyuan
 * @data 2020-06-09 17:50
 */
@Data
public class UserToBO {
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
