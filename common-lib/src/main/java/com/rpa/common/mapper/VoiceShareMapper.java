package com.rpa.common.mapper;

import com.rpa.common.pojo.VoiceSharePO;
import org.apache.ibatis.annotations.Mapper;

/**
 * VoiceShareMapper继承基类
 */
@Mapper
public interface VoiceShareMapper extends BaseMapper<VoiceSharePO, Long> {
    /**
     * 根据分享码查询
     *
     * @param shareCode
     * @return
     */
    VoiceSharePO queryByCode(String shareCode);
}