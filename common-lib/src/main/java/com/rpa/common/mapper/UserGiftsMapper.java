package com.rpa.common.mapper;

import com.rpa.common.pojo.UserGiftsPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * UserGiftsMapper继承基类
 */
@Mapper
public interface UserGiftsMapper extends BaseMapper<UserGiftsPO, Integer> {
}