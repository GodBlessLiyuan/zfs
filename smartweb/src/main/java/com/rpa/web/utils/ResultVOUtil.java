package com.rpa.web.utils;

import com.rpa.web.enumeration.ExceptionEnum;
import com.rpa.web.vo.ResultVO;

/**
 * @author: xiahui
 * @date: Created in 2019/10/11 11:03
 * @description: 返回结果处理
 * @version: 1.0
 */
public class ResultVOUtil {

    public static ResultVO<Object> success() {
        return success(null);
    }

    public static ResultVO<Object> success(Object data) {
        ResultVO<Object> vo = new ResultVO<>(ExceptionEnum.SUCCESS);
        vo.setData(data);
        return vo;
    }

    public static ResultVO error(ExceptionEnum exceptionEnum) {
        return new ResultVO(exceptionEnum);
    }

    public static ResultVO error(Integer code, String msg) {
        return new ResultVO(code, msg);
    }
}
