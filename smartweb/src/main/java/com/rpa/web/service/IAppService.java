package com.rpa.web.service;

import com.rpa.web.dto.AppDTO;
import com.rpa.web.utils.DTPageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/10/1 13:49
 * @description: 版本更新
 * @version: 1.0
 */
public interface IAppService {

    /**
     * 分页查询
     * @param draw
     * @param pageNum
     * @param pageSize
     * @param reqData
     * @return
     */
    DTPageInfo<AppDTO> query(int draw, int pageNum, int pageSize, Map<String, Object> reqData);

    /**
     * 插入
     * @param url apk文件路径
     * @param updateType 更新方式
     * @param softChannel 更新渠道
     * @param context 更新内容
     * @param extra 备注
     * @return
     */
    int insert(String url, byte updateType, int[] softChannel, String context, String extra);

    /**
     * 更新状态（发布&取消发布）
     * @param appId
     * @param status
     * @return
     */
    int updateStatus(int appId, int status);

    /**
     * 删除
     * @param appId
     * @return
     */
    int delete(int appId);

    /**
     * 从应用渠道表查询所有AppId数据
     * @return
     */
    List<AppDTO> queryAll();

    /**
     * 根据ID查询数据
     * @param appId
     * @return
     */
    List<AppDTO> queryById(int appId);

    /**
     * 更新
     * @param appId 应用Id
     * @param url 链接地址
     * @param updateType 更新类型
     * @param softChannel 渠道
     * @param context 内容
     * @param extra 备注
     * @return
     */
    int update(int appId, String url, byte updateType, int[] softChannel, String context, String extra);
}
