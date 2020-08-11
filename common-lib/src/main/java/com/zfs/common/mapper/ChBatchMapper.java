package com.zfs.common.mapper;

import com.zfs.common.pojo.ChBatchPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * ChBatchMapper继承基类
 */
@Mapper
public interface ChBatchMapper extends BaseMapper<ChBatchPO, Integer> {

    Integer queryTypeIdByBatchId(Integer batchId);

    Integer queryDaysByTypeId(Integer typeId);

    Byte queryActiveByPri(Integer batchId);
    //查询创建时间+天数小于现在时刻，从数据库构造时间
    List<Map<String,Integer>> getListStatus();
}