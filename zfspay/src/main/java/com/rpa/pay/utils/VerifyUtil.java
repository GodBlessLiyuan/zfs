package com.rpa.pay.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.rpa.pay.dto.base.TokenDTO;
import com.rpa.pay.dto.base.VerifyDTO;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

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
     * @param dto
     * @return
     */
    public static boolean checkDeviceId(VerifyDTO dto) {
        if (dto.getId() == null || dto.getVerify() == null) {
            return false;
        }

        return dto.getVerify().equals(DigestUtils.md5DigestAsHex(dto.getId().toString().getBytes()));
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
