package com.rpa.pay.mapper;

import com.rpa.pay.pojo.AliFeedbackPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * AliFeedbackMapper继承基类
 */
@Mapper
public interface AliFeedbackMapper extends MyBatisBaseDao<AliFeedbackPO, AliFeedbackPO> {
}