package com.zfs.web.service;

import com.zfs.common.vo.ResultVO;
import com.zfs.web.utils.DTPageInfo;
import com.zfs.web.vo.AvatarVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/12/12 19:30
 * @description: 分身更新
 * @version: 1.0
 */
public interface IAvatarService {

    /**
     * 分页查询
     *
     * @param draw
     * @param pageNum
     * @param pageSize
     * @param reqData
     * @return
     */
    DTPageInfo<AvatarVO> query(int draw, int pageNum, int pageSize, Map<String, Object> reqData);

    /**
     * 根据ID查询数据
     *
     * @param avatarId
     * @return
     */
    List<AvatarVO> queryById(long avatarId);

    /**
     * 插入
     *
     * @param file        apk文件
     * @param updateType  更新方式
     * @param appId       应用版本
     * @param softChannel 更新渠道
     * @param context     更新内容
     * @param extra       备注
     * @param aId         管理员
     * @return
     */
    ResultVO insert(MultipartFile file, int osVersion,byte updateType, int appId, int[] softChannel, String context, String extra, int aId);

    /**
     * 更新状态（发布&取消发布）
     *
     * @param avatarId
     * @param status
     * @return
     */
    ResultVO updateStatus(long avatarId, byte status);

    /**
     * 删除
     *
     * @param avatarId
     * @return
     */
    ResultVO delete(long avatarId);

    /**
     * 更新
     *
     * @param avatarId    分身Id
     * @param file        链接地址
     * @param updateType  更新类型
     * @param appId       应用ID
     * @param softChannel 渠道
     * @param context     内容
     * @param extra       备注
     * @return
     */
    ResultVO update(long avatarId, MultipartFile file, byte updateType, int appId, int[] softChannel, String context, String extra);

    /**
     * 查询渠道信息
     * @param avatarId
     * @param appId
     * @return
     */
    List<Integer> queryChanIds(long avatarId, int appId);
}
