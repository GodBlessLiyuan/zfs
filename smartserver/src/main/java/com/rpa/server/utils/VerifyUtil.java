package com.rpa.server.utils;

import com.rpa.server.constant.LoginConstant;
import org.springframework.util.DigestUtils;

import java.util.regex.Matcher;

/**
 * @author: xiahui
 * @date: Created in 2019/10/16 9:49
 * @description: 验证工具
 * @version: 1.0
 */
public class VerifyUtil {

    /**
     * 验证设备Id与设备Md5是否一致
     *
     * @param id
     * @param md5
     * @return
     */
    public static boolean checkDeviceId(Long id, String md5) {
        if (id == null || md5 == null) {
            return false;
        }

        return md5.equals(DigestUtils.md5DigestAsHex(id.toString().getBytes()));
    }

    /**
     * 验证手机号是否符合规范
     *
     * @param phone 手机号
     * @return
     */
    public static boolean checkPhone(String phone) {
        if (phone == null || phone.length() != LoginConstant.PHONE_LENGTH) {
            return false;
        }

        Matcher matcher = LoginConstant.CHINESE_PHONE_PATTERN.matcher(phone);
        return matcher.matches();
    }
}
