package com.rpa.web.mapper;

import com.rpa.web.pojo.PromoterPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * PromoterMapper继承基类
 */
@Mapper
public interface PromoterMapper extends BaseDAO<PromoterPO, Integer> {
    List<PromoterPO> queryAllProNames();
}