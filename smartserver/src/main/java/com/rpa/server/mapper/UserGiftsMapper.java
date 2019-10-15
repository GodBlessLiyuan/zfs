package com.rpa.server.mapper;

import com.rpa.server.pojo.UserGiftsPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * UserGiftsMapper继承基类
 */
@Mapper
public interface UserGiftsMapper extends BaseMapper<UserGiftsPO, Integer> {
}