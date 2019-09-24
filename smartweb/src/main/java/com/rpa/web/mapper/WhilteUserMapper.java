package com.rpa.web.mapper;

import com.rpa.web.pojo.WhilteUserPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * WhilteUserMapper继承基类
 */
@Mapper
public interface WhilteUserMapper extends BaseDAO<WhilteUserPO, WhilteUserPO> {
}