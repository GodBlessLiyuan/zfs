package com.zfs.web.service.impl;

import com.alibaba.fastjson.JSON;
import com.zfs.common.mapper.BatchInfoMapper;
import com.zfs.common.mapper.ChBatchMapper;
import com.zfs.common.pojo.ChBatchPO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @author: liyuan
 * @data 2020-08-10 18:37
 */
@Service
public class ChBatchScheduleServiceImpl {

    private static final Logger logger = LoggerFactory.getLogger(ChBatchScheduleServiceImpl.class);
    /***
     * 这是定时统计卡密批次的定时器，只计算状态为
     *      5结束：数量没了
     *      6在失效之后，再过期则是过期失效；
     *      7在冻结之后，再过期则是过期冻结。
     * 思路：分组父表t_ch_batch，计算批次下的更新时间之后，统计t_ch_batch状态值为
     *    (1 未激活 2 激活  3 冻结  4 失效 5 结束 6 过期失效 7 冻结失效）
     *        冻结(3)和过期冻结（7）
     *         失效(4)和过期失效（6）
     * */

    //函数名：过期统计
    @Autowired
    private ChBatchMapper chBatchMapper;
    @Autowired
    private BatchInfoMapper batchInfoMapper;

    @Scheduled(cron = "${chBatchSchedule.time}")
    public void overLineCount() {
        //联合表查询，创建时间+天数小于现在时刻，从数据库构造时间
        List<Map<String, Integer>> chBatchIdS = chBatchMapper.getListStatus();
        if (chBatchIdS == null || chBatchIdS.size() == 0) {
            logger.info("批次信息表的状态更新完毕");
            return;
        }
        // key:batchId,status
        for (Map<String, Integer> map : chBatchIdS) {
            //查询该状态下的值，并且更新该状态下的值
            if (map.get("status") == 1 || map.get("status") == 2) {
                List<Integer> infoS1 = batchInfoMapper.queryStatus(map.get("batchId"), 1);
                minLineCount(infoS1, map.get("batchId"), (byte) 5);
            } else if (map.get("status") == 3) {
                List<Integer> infoS1 = batchInfoMapper.queryStatus(map.get("batchId"), 3);
                minLineCount(infoS1, map.get("batchId"), (byte) 7);
            } else if (map.get("status") == 4) {
                List<Integer> infoS1 = batchInfoMapper.queryStatus(map.get("batchId"), 4);
                minLineCount(infoS1, map.get("batchId"), (byte) 6);
            }
        }
        logger.info("更新了t_ch_batch表主键为{}的状态:，t_batch_info表的主键为{}的状态：", JSON.toJSONString(chBatchIdS));
    }

    private void minLineCount(List<Integer> infoS, Integer chBatch, Byte toStatu) {
        if (infoS == null || infoS.size() == 0) {
            ChBatchPO chBatchPO = new ChBatchPO();
            chBatchPO.setBatchId(chBatch);
            chBatchPO.setStatus(toStatu);
            //更新父级表t_ch_batch
            chBatchMapper.updateByPrimaryKeySelective(chBatchPO);
            return;
        }
        //批量更新t_batch_info表
        for (Integer info : infoS) {
            batchInfoMapper.updateStatusByBatchId(toStatu, info);
        }
        ChBatchPO chBatchPO = new ChBatchPO();
        chBatchPO.setBatchId(chBatch);
        chBatchPO.setStatus(toStatu);
        //更新父级表t_ch_batch
        chBatchMapper.updateByPrimaryKeySelective(chBatchPO);
        logger.info("更新了t_batch_info表的主键为{}的状态：", JSON.toJSONString(infoS));
    }


}
