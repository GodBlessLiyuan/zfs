package com.rpa.web.mapper;

import com.rpa.web.pojo.ShareActivityPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * ShareActivityMapper继承基类
 */
@Mapper
public interface ShareActivityMapper extends BaseDAO<ShareActivityPO, Integer> {
}