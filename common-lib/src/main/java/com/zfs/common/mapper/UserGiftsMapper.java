package com.zfs.common.mapper;

import com.zfs.common.pojo.UserGiftsPO;
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

    /**
     * 根据已开启的用户配置表
     *
     * @return
     */
    List<UserGiftsPO> queryOpenGift();
}