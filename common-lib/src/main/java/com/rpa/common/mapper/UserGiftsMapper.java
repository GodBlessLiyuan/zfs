package com.rpa.common.mapper;

import com.rpa.common.pojo.UserGiftsPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * UserGiftsMapper继承基类
 */
@Mapper
public interface UserGiftsMapper extends BaseMapper<UserGiftsPO, Integer> {
    /**
     * 根据状态查询数据
     *
     * @param status
     * @return
     */
    List<UserGiftsPO> queryByStatus(Byte status);
}