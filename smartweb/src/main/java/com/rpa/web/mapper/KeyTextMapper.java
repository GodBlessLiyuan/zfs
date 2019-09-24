package com.rpa.web.mapper;

import com.rpa.web.pojo.KeyTextPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * KeyTextMapper继承基类
 */
@Mapper
public interface KeyTextMapper extends BaseDAO<KeyTextPO, Integer> {
}