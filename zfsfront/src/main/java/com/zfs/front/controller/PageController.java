package com.zfs.front.controller;

import com.zfs.common.utils.VerifyUtil;
import com.zfs.common.vo.ResultVO;
import com.zfs.common.constant.ErrorCode;

import com.zfs.front.constant.IncomeConstant;
import com.zfs.front.dto.UserActivityDTO;
import com.zfs.front.service.UserActivityService;
import com.zfs.front.vo.IncomeVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: xiahui
 * @date: Created in 2019/10/25 18:48
 * @description: 多开分身
 * @version: 1.0
 */
@RequestMapping("v1.0")
@Controller
public class PageController {
    private static final Logger logger = LoggerFactory.getLogger(PageController.class);

    @Autowired
    private UserActivityService userActivityService;

    /**
     * 用户注册协议
     */
    @GetMapping("useragreement")
    public String userAgreement() {
        return "user_register_agreement_index";
    }

    /**
     * 会员中心——会员权益
     *
     * @return
     */
    @GetMapping("memberright")
    public String memberRight() {
        return "member_right_index";
    }

    /**
     * @description：免费领取会员
     */
    @PostMapping("freemember")
    public String freemember(@RequestBody UserActivityDTO dto, ModelMap map, HttpServletRequest req) {
        if (null == req.getHeader(IncomeConstant.INCOME_TOKEN) || "".equals(req.getHeader(IncomeConstant.INCOME_TOKEN))) {
            map.put(IncomeConstant.INCOME_RESULT, new ResultVO<>(ErrorCode.USER_NOT_LOGIN, new IncomeVO()));
            return "free_membership_index";
        }

        if (!VerifyUtil.checkToken(dto, req)) {
            return null;
        }

        Integer status = this.userActivityService.query(dto);
        map.put("status", status);
        if (null == status) {
            map.put("msg", "期待您的参与！");
        } else if (status == 1) {
            map.put("msg", "参与成功，正在审核中！");
        } else if (status == 10 || status == 30) {
            map.put("msg", "参与成功，奖励已发放！");
        } else if (status == 20) {
            map.put("msg", "您上传的图片不符合标准，请重新上传！");
        }

        return "free_membership_index";
    }

    /**
     * 设置-多开分身服务使用协议
     *
     * @return
     */
    @GetMapping("settingprotocol")
    public String settingProtocol() {
        return "use_service_protocol_index";
    }

    /**
     * 会员中心——会员服务协议
     *
     * @return
     */
    @GetMapping("memberserviceportocol")
    public String memberProtocol() {
        return "member_service_protocol";
    }

    /**
     * 隐私
     *
     * @return
     */
    @GetMapping("privacy")
    public String privacy() {
        return "privacy_index";
        
    }
}

