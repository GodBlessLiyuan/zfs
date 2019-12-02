package com.rpa.common.utils;

import com.rpa.common.constant.ModuleConstant;

/**
 * @author: xiahui
 * @date: Created in 2019/11/27 9:13
 * @description: Redis Key 命名规则
 * @version: 1.0
 */
public class RedisKeyUtil {

    public static String genAppRedisKey(Object... params) {
        return RedisKeyUtil.genRedisKey(ModuleConstant.APP, params);
    }

    public static String genPluginRedisKey(Object... params) {
        return RedisKeyUtil.genRedisKey(ModuleConstant.PLUGIN, params);
    }

    public static String genVipCommodityRedisKey(Object... params) {
        return RedisKeyUtil.genRedisKey(ModuleConstant.VIP_COMMODITY, params);
    }

    public static String genWhiteDeviceRedisKey(Object... params) {
        return RedisKeyUtil.genRedisKey(ModuleConstant.WHITE_DEVICE, params);
    }

    public static String genOtherAppRedisKey(Object... params) {
        return RedisKeyUtil.genRedisKey(ModuleConstant.OTHER_APP, params);
    }

    public static String genAdconfigRedisKey(Object... params) {
        return RedisKeyUtil.genRedisKey(ModuleConstant.AD_CONFIG, params);
    }

    public static String genBannerconfigRedisKey(Object... params) {
        return RedisKeyUtil.genRedisKey(ModuleConstant.BANNER_CONFIG, params);
    }

    public static String genNoticeRedisKey(Object... params) {
        return RedisKeyUtil.genRedisKey(ModuleConstant.NOTICE, params);
    }

    public static String genFunctionvideoRedisKey(Object... params) {
        return RedisKeyUtil.genRedisKey(ModuleConstant.FUNCTION_VIDEO, params);
    }

    public static String genSupportRedisKey(Object... params) {
        return RedisKeyUtil.genRedisKey(ModuleConstant.SUPPORT, params);
    }

    public static String genShareRedisKey(Object... params) {
        return RedisKeyUtil.genRedisKey(ModuleConstant.SHARE, params);
    }

    /**
     * 生成RedisKey
     *
     * @param module 模块名
     * @param params 额外参数
     * @return
     */
    public static String genRedisKey(String module, Object... params) {
        StringBuilder redisKey = new StringBuilder();
        redisKey.append(ModuleConstant.PROJECT);
        redisKey.append("_");
        redisKey.append(module);
        redisKey.append("_");
        for (Object param : params) {
            redisKey.append(param);
            redisKey.append("_");
        }
        redisKey.deleteCharAt(redisKey.length() - 1);

        return redisKey.toString();
    }
}
