package com.zfs.common.mapper;

import com.zfs.common.pojo.ServiceNumberPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ServiceNumberMapper继承基类
 */
@Mapper
public interface ServiceNumberMapper extends BaseMapper<ServiceNumberPO, Long> {
    List<String> queryNumbers();
}