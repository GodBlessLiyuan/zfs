package com.zfs.server.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.zfs.common.dto.TokenDTO;
import com.zfs.server.constant.LoginConstant;
import com.zfs.common.dto.VerifyDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;

/**
 * @author: xiahui
 * @date: Created in 2019/10/16 9:49
 * @description: 验证工具
 * @version: 1.0
 */
@Component
public class VerifyUtil {

    private static String salt;
    private static Integer TokenExpire;

    @Value("${token.expireTime}")
    private void setTokenExpire(Integer tokenExpire) {
        TokenExpire = tokenExpire;
    }

    public static Date geneTokenExpire() {
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.MINUTE,TokenExpire);
        return calendar.getTime();
    }

    @Value("${verify.config.salt}")
    private void setSalt(String salt) {
        VerifyUtil.salt = salt;
    }

    /**
     * 验证设备Id与设备Md5是否一致
     *
     * @param dto
     * @return
     */
    public static boolean checkDeviceId(VerifyDTO dto) {
        if (dto.getId() == null || dto.getVerify() == null) {
            return false;
        }

        return dto.getVerify().equals(DigestUtils.md5DigestAsHex((salt + dto.getId()).getBytes()));
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
    public static boolean expire(TokenDTO dto, HttpServletRequest req) {
        String token = req.getHeader("token");
        if (token == null) {
            return false;
        }
        DecodedJWT decode = JWT.decode(token);
        Date expiresAt = decode.getExpiresAt();
        if(expiresAt.before(new Date())){
            return false;//存活时间失效
        }else{
            return true;
        }
    }
}
