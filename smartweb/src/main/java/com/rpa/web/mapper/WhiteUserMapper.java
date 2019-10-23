package com.rpa.web.mapper;

import com.rpa.web.pojo.WhiteUserPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * WhiteUserMapper继承基类
 */
@Mapper
public interface WhiteUserMapper extends BaseDAO<WhiteUserPO, WhiteUserPO> {
}