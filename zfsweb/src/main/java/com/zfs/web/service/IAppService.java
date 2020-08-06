package com.zfs.web.service;
import com.zfs.common.vo.ResultVO;
import com.zfs.web.vo.AppVO;
import org.springframework.web.multipart.MultipartFile;

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
     * @param pageNum
     * @param pageSize
     * @param reqData
     * @return
     */
    ResultVO query( int pageNum, int pageSize, Map<String, Object> reqData);

    /**
     * 插入
     *
     * @param file        apk文件
     * @param updateType  更新方式
     * @param softChannel 更新渠道
     * @param context     更新内容
     * @param extra       备注
     * @param aId         管理员
     * @return
     */
    ResultVO insert(MultipartFile file, byte updateType, int[] softChannel, String context, String extra, int aId);

    /**
     * 更新状态（发布&取消发布）
     *
     * @param appId
     * @param status
     * @return
     */
    ResultVO updateStatus(int appId, int status);

    /**
     * 删除
     *
     * @param appId
     * @return
     */
    ResultVO delete(int appId);

    /**
     * 从应用渠道表查询所有AppId数据
     *
     * @return
     */
    List<AppVO> queryAll();

    /**
     * 根据ID查询数据
     *
     * @param appId
     * @return
     */
    AppVO queryById(int appId);

    /**
     * 更新
     *
     * @param appId       应用Id
     * @param file        链接地址
     * @param updateType  更新类型
     * @param softChannel 渠道
     * @param context     内容
     * @param extra       备注
     * @return
     */
    ResultVO update(int appId, MultipartFile file, byte updateType, int[] softChannel, String context, String extra);
}
