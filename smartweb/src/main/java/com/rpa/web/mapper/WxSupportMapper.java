package com.rpa.web.mapper;

import com.rpa.web.pojo.WxSupportPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * WxSupportMapper继承基类
 */
@Mapper
public interface WxSupportMapper extends BaseDAO<WxSupportPO, Integer> {
}