package com.zfs.web.service.impl;

/**
 * @Description:
 * @author: liyuan
 * @data 2020-08-10 18:37
 */
public class ChBatchScheduleServiceImpl {
    /***
     * 这是定时统计卡密批次的定时器，只计算状态为
     *  5数量没了是结束。
     * 6在失效之后，再过期则是过期失效；
     * 7在冻结之后，再过期则是过期冻结。
     * 思路：分组父表t_ch_batch，计算批次下的更新时间之后，统计t_batch_info状态值为（
     *    1 未激活 2 激活  3 冻结  4 失效 5 结束 6 过期失效 7 冻结失效）1未激活的
     *  数量没有，则是结束；有失效、过期则是失效过期
     * */

}
