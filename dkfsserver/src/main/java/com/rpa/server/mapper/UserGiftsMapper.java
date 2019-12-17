package com.rpa.server.mapper;

import com.rpa.common.pojo.UserGiftsPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * UserGiftsMapper继承基类
 */
@Mapper
public interface UserGiftsMapper extends BaseMapper<UserGiftsPO, Integer> {
    /**
     * 根据已开启的用户配置表
     *
     * @return
     */
    List<UserGiftsPO> queryOpenGift();
}