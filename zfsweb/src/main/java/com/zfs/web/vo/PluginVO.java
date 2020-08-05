package com.zfs.web.vo;

import com.zfs.common.bo.PluginBO;
import lombok.Data;

import java.io.Serializable;
import java.util.*;

/**
 * @author: xiahui
 * @date: Created in 2019/10/6 20:08
 * @description: 插件更新
 * @version: 1.0
 */
@Data
public class PluginVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer pluginId;
    private Date publishTime;
    private String username;
    private Integer size;
    private String context;
    // 应用Id和渠道Id组合
    private String ids;
    private String name;
    private Byte status;
    private String extra;

    /**
     * po 转 dto
     *
     * @param bo
     * @return
     */
    public static PluginVO convert(PluginBO bo) {
        PluginVO vo = new PluginVO();

        vo.setPluginId(bo.getPluginId());
        vo.setPublishTime(bo.getPublishTime());
        vo.setUsername(bo.getUsername());
        vo.setSize(bo.getSize());
        vo.setContext(bo.getContext());
        vo.setName(bo.getVersionName() + "|" + bo.getName());
        vo.setIds(bo.getAppId() + "|" + bo.getSoftChannelId());
        vo.setStatus(bo.getStatus());
        vo.setExtra(bo.getExtra());

        return vo;
    }

    /**
     * pos 转 dtos
     *
     * @param pos
     * @return
     */
    public static List<PluginVO> convert(List<PluginBO> pos) {
        // 合并相同的pluginId
        Map<Integer, PluginVO> map = new HashMap<>();

        for (PluginBO po : pos) {
            int pluginId = po.getPluginId();
            if (map.containsKey(pluginId)) {
                PluginVO dto = map.get(pluginId);
                dto.setName(dto.getName() + "," + po.getVersionName() + "|" + po.getName());
                dto.setIds(dto.getIds() + "," + po.getAppId() + "|" + po.getSoftChannelId());
            } else {
                map.put(pluginId, PluginVO.convert(po));
            }
        }

        return new ArrayList<>(map.values());
    }
}
