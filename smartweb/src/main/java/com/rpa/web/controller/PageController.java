package com.rpa.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/9/25 19:34
 * @description: 页面导航
 * @version: 1.0
 */
@Controller
public class PageController {

    /**
     * 用户信息
     *
     * @return
     */
    @RequestMapping("/userinfo")
    public String userInfo() {
        return "user_info_index";
    }

    /**
     * 产品列表
     *
     * @return
     */
    @RequestMapping("/comtype")
    public String comType() {
        return "com_type_index";
    }

    /**
     * 商品列表
     *
     * @return
     */
    @RequestMapping("/vipcommodity")
    public String commodity() {
        return "vip_commodity_index";
    }

    /**
     * 渠道信息
     *
     * @return
     */
    @RequestMapping("/softchannel")
    public String softChannel() {
        return "soft_channel_index";
    }

    /**
     * 未注册用户信息
     *
     * @return
     */
    @RequestMapping("/unregistered")
    public String unregistered() {
        return "unregistered_index";
    }

    /**
     * 其他产品
     *
     * @return
     */
    @RequestMapping("/otherapp")
    public String otherApp() {
        return "other_app_index";
    }

    /**
     * 微信白名单
     *
     * @return
     */
    @RequestMapping("/wxsupport")
    public String wxSupport() {
        return "wxsupport_index";
    }

    /**
     * 测试白名单
     *
     * @return
     */
    @RequestMapping("/whiltedevice")
    public String whilteDevice() {
        return "whilte_device_index";
    }

    /**
     * 版本更新
     *
     * @return
     */
    @RequestMapping("/appversion")
    public String app() {
        return "app_index";
    }

    /**
     * 新用户送会员
     *
     * @return
     */
    @RequestMapping("/newusergifts")
    public String userGifts() {
        return "new_user_gifts_index";
    }

    /**
     * 新用户赠送记录
     *
     * @return
     */
    @RequestMapping("/newuserrecord")
    public String newUserRecord() {
        return "new_user_record_index";
    }

    /**
     * 订单信息
     *
     * @return
     */
    @RequestMapping("/order")
    public String order() {
        return "order_index";
    }

    /**
     * 插件更新
     * @return
     */
    @RequestMapping("/plugin")
    public String plugin() {
        return "plugin_index";
    }

    /**
     * 活动赠送记录
     * @return
     */
    @RequestMapping("/useractivity")
    public String userActivity() {
        return "user_activity_index";
    }

    /**
     * 用户会员数据
     * @return
     */
    @RequestMapping("/uservip")
    public String userVip() {
        return "user_vip_index";
    }

    /**
     * 开屏广告
     * @return
     */
    @RequestMapping("/adconfig")
    public String adconfig() {
        return "adconfig_index";
    }

    /**
     * 广告开放渠道
     * @return
     */
    @RequestMapping("/adchannel")
    public String adchannel(@RequestParam(value = "adId") Integer adId, Map<String, Integer> map) {
        if (null == adId) {
            return "error_404_index";
        }
        map.put("adId", adId);
        return "adchannel_index";
    }

    /**
     * 首页banner
     * @return
     */
    @RequestMapping("/bannerconfig")
    public String bannerConfig() {
        return "banner_index";
    }

    /**
     * 通知管理
     * @return
     */
    @RequestMapping("/notice")
    public String notice() {
        return "notice_index";
    }

    /**
     * 功能视频
     * @return
     */
    @RequestMapping("/functionvideo")
    public String functionvideo() {
        return "functionvideo_index";
    }

    /**
     * 问题反馈
     * @return
     */
    @RequestMapping("/feedback")
    public String feedback() {
        return "feedback_index";
    }

    /**
     * 好评活动
     * @return
     */
    @RequestMapping("/goodcomment")
    public String goodcomment() {
        return "goodcomment_index";
    }

    /**
     * 养号教程
     * @return
     */
    @RequestMapping("/accounttutorial")
    public String accounttutorial() {
        return "account_tutorial_index";
    }

    /**
     * 推广负责人
     * @return
     */
    @RequestMapping("/promoter")
    public String promoter() {
        return "promoter_index";
    }

    /**
     * 会员卡查询
     * @return
     */
    @RequestMapping("/batchinfo")
    public String batchinfo() {
        return "batchinfo_index";
    }

    /**
     * 会员卡配置
     * @return
     */
    @RequestMapping("/chbatch")
    public String chbatch() {
        return "chbatch_index";
    }

    /**
     * 推广渠道
     * @return
     */
    @RequestMapping("/channel")
    public String channel() {
        return "channel_index";
    }

    /**
     * 账号管理
     * @return
     */
    @RequestMapping("/admin")
    public String admin() {
        return "adminuser_index";
    }


    /**
     * 卡密表详情
     * @return
     */
    @RequestMapping("/batchinfo/detail")
    public String batchinfo(@RequestParam(value = "batchId") Integer batchId, Map<String, Integer> map) {
        if (null == batchId) {
            return "error_404_index";
        }
        map.put("batchId", batchId);
        return "batchinfo_detail_index";
    }


    /**
     * 错误页面：404
     * @return
     */
    @RequestMapping("/error_404")
    public String error_404() {
        return "error_404_index";
    }

}
