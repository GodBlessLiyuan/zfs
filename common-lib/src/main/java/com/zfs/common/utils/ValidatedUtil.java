package com.zfs.common.utils;

import com.zfs.common.vo.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author: velve
 * @date: Created in 2020/8/6 10:29
 * @description: TODO
 * @version: 1.0
 */
public class ValidatedUtil {
    private static final Logger LOG = LoggerFactory.getLogger(ValidatedUtil.class);

    // 实际使用建议将编码信息放置在一个单独的文件中统一管理
    /**
     * 操作成功
     */
    public static int SUCCESS = 200;
    /**
     * 参数无效
     */
    public static int PARAM_INVALID = 1001;

    // =================== Spring validated (建议使用) ===================
    /**
     * <h5>功能:验证参数信息是否有效</h5>
     *
     * @param bindingResult
     * @return
     */
    public static ResultVO validate(BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            ResultVO messageBean = new ResultVO();
            // 设置验证结果状态码
            messageBean.setStatus(1003);
            // 获取错误字段信息集合
            List<FieldError> fieldErrorList = bindingResult.getFieldErrors();

            // 使用TreeSet是为了让输出的内容有序输出(默认验证的顺序是随机的)
            Set<String> errorInfoSet = new TreeSet<String>();
            for (FieldError fieldError : fieldErrorList) {
                // 遍历错误字段信息
                errorInfoSet.add(fieldError.getDefaultMessage());
                break;
            }

            StringBuffer sbf = new StringBuffer();
            for (String errorInfo : errorInfoSet) {
                sbf.append(errorInfo);
                sbf.append(",");
            }
            messageBean.setData(sbf.substring(0, sbf.length() - 1));
            return messageBean;
        }

        return null;
    }
}
