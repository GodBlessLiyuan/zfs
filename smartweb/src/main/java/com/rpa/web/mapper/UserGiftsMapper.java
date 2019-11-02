package com.rpa.web.mapper;

import com.rpa.web.pojo.UserGiftsPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * UserGiftsMapper继承基类
 */
@Mapper
public interface UserGiftsMapper extends BaseDAO<UserGiftsPO, Integer> {
    /**
     * 根据状态查询数据
     * @param status
     * @return
     */
    List<UserGiftsPO> queryByStatus(Byte status);
}