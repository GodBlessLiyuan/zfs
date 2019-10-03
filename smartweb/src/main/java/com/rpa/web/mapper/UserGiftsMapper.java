package com.rpa.web.mapper;

import com.rpa.web.pojo.UserGiftsPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * UserGiftsMapper继承基类
 */
@Mapper
public interface UserGiftsMapper extends BaseDAO<UserGiftsPO, Integer> {
}