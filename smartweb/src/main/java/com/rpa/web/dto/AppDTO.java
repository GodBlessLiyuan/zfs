package com.rpa.web.dto;

import com.rpa.web.pojo.AppPO;

import java.io.Serializable;
import java.util.*;

/**
 * @author: xiahui
 * @date: Created in 2019/10/1 13:48
 * @description: 版本更新
 * @version: 1.0
 */
public class AppDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer appId;
    private Date publishTime;
    private String username;
    private String versionName;
    private Integer size;
    private Byte updateType;
    private String context;
    private String chanId;
    private String chanName;
    private Integer status;
    private String extra;

    /**
     * po 转 dto
     *
     * @param po
     * @return
     */
    public static AppDTO convert(AppPO po) {
        AppDTO dto = new AppDTO();

        dto.setAppId(po.getAppId());
        dto.setChanId(String.valueOf(po.getChanId()));
        dto.setChanName(po.getChanName());
        dto.setContext(po.getContext());
        dto.setExtra(po.getExtra());
        dto.setPublishTime(po.getPublishTime());
        dto.setSize(po.getSize());
        dto.setStatus(po.getStatus());
        dto.setUpdateType(po.getUpdateType());
        dto.setUsername(po.getUsername());
        dto.setVersionName(po.getVersionname());

        return dto;
    }

    /**
     * pos 转 dtos
     *
     * @param pos
     * @return
     */
    public static List<AppDTO> convert(List<AppPO> pos) {
        // 合并相同的appId
        Map<Integer, AppDTO> dtos = new HashMap<>();
        for (AppPO po : pos) {
            int appId = po.getAppId();
            if (dtos.containsKey(appId)) {
                AppDTO dto = dtos.get(appId);
                dto.setChanId(dto.getChanId() + "," + po.getChanId());
                dto.setChanName(dto.getChanName() + "," + po.getChanName());
            } else {
                dtos.put(appId, AppDTO.convert(po));
            }
        }

        return new ArrayList<>(dtos.values());
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
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

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Byte getUpdateType() {
        return updateType;
    }

    public void setUpdateType(Byte updateType) {
        this.updateType = updateType;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getChanId() {
        return chanId;
    }

    public void setChanId(String chanId) {
        this.chanId = chanId;
    }

    public String getChanName() {
        return chanName;
    }

    public void setChanName(String chanName) {
        this.chanName = chanName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}
