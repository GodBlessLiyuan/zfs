package com.rpa.front.utils;

import com.auth0.jwt.JWT;
import com.rpa.front.dto.base.TokenDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: xiahui
 * @date: Created in 2019/10/16 9:49
 * @description: 验证工具
 * @version: 1.0
 */
public class VerifyUtil {

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
