package com.zfs.common.mapper;

import com.zfs.common.pojo.PhoneInfoPO;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface PhoneInfoMapper extends BaseMapper<PhoneInfoPO,Integer> {
    public PhoneInfoPO queryByModal(Long modelID);
}