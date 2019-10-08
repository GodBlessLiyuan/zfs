package com.rpa.web.dto;

import com.rpa.web.pojo.PluginPO;

import java.io.Serializable;
import java.util.*;

/**
 * @author: xiahui
 * @date: Created in 2019/10/6 20:08
 * @description: 插件更新
 * @version: 1.0
 */
public class PluginDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer pluginId;
    private Date publishTime;
    private String username;
    private Integer size;
    private String context;
    private String name;
    private Byte status;
    private String extra;

    /**
     * po 转 dto
     *
     * @param po
     * @return
     */
    public static PluginDTO convert(PluginPO po) {
        PluginDTO dto = new PluginDTO();

        dto.setPluginId(po.getPluginId());
        dto.setPublishTime(po.getPublishTime());
        dto.setUsername(po.getUsername());
        dto.setSize(po.getSize());
        dto.setContext(po.getContext());
        dto.setName(po.getVersionName() + "|" + po.getName());
        dto.setStatus(po.getStatus());
        dto.setExtra(po.getExtra());

        return dto;
    }

    /**
     * pos 转 dtos
     *
     * @param pos
     * @return
     */
    public static List<PluginDTO> convert(List<PluginPO> pos) {
        // 合并相同的pluginId
        Map<Integer, PluginDTO> dtos = new HashMap<>();

        for (PluginPO po : pos) {
            int pluginId = po.getPluginId();
            if (dtos.containsKey(pluginId)) {
                PluginDTO dto = dtos.get(pluginId);
                dto.setName(dto.getName() + ", " + po.getVersionName() + "|" + po.getName());
            } else {
                dtos.put(pluginId, PluginDTO.convert(po));
            }
        }

        return new ArrayList<>(dtos.values());
    }

    public Integer getPluginId() {
        return pluginId;
    }

    public void setPluginId(Integer pluginId) {
        this.pluginId = pluginId;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}
