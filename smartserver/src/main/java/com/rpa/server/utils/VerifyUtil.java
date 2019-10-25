package com.rpa.server.utils;

import com.auth0.jwt.JWT;
import com.rpa.server.constant.LoginConstant;
import com.rpa.server.dto.TokenDTO;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
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


    /**
     * token 验证
     *
     * @param dto
     * @param req
     * @return
     */
    public static boolean checkToken(TokenDTO dto, HttpServletRequest req) {
        String token = req.getHeader("token");
        if (token == null) {
            return false;
        }

        List<String> audience = JWT.decode(token).getAudience();
        return dto.getUd().toString().equals(audience.get(0)) && dto.getId().toString().equals(audience.get(1))
                && dto.getUdd().toString().equals(audience.get(2));
    }
}
