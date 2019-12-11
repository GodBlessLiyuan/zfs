package com.rpa.web.vo;

import com.rpa.common.bo.AppBO;
import lombok.Data;

import java.io.Serializable;
import java.util.*;

/**
 * @author: xiahui
 * @date: Created in 2019/10/1 13:48
 * @description: 版本更新
 * @version: 1.0
 */
@Data
public class AppVO implements Serializable {

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
     * @param bo
     * @return
     */
    public static AppVO convert(AppBO bo) {
        AppVO dto = new AppVO();

        dto.setAppId(bo.getAppId());
        dto.setChanId(String.valueOf(bo.getChanId()));
        dto.setChanName(bo.getChanName());
        dto.setContext(bo.getContext());
        dto.setExtra(bo.getExtra());
        dto.setPublishTime(bo.getPublishTime());
        dto.setSize(bo.getSize());
        dto.setStatus(bo.getStatus());
        dto.setUpdateType(bo.getUpdateType());
        dto.setUsername(bo.getUsername());
        dto.setVersionName(bo.getVersionname());

        return dto;
    }

    /**
     * pos 转 dtos
     *
     * @param bos
     * @return
     */
    public static List<AppVO> convert(List<AppBO> bos) {
        // 合并相同的appId
        Map<Integer, AppVO> dtos = new HashMap<>();
        for (AppBO bo : bos) {
            int appId = bo.getAppId();
            if (dtos.containsKey(appId)) {
                AppVO dto = dtos.get(appId);
                dto.setChanId(dto.getChanId() + "," + bo.getChanId());
                dto.setChanName(dto.getChanName() + "," + bo.getChanName());
            } else {
                dtos.put(appId, AppVO.convert(bo));
            }
        }

        return new ArrayList<>(dtos.values());
    }
}
