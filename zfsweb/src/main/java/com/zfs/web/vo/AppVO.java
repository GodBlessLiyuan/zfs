package com.zfs.web.vo;

import com.zfs.common.bo.AppBO;
import com.zfs.web.utils.MultiUtil;
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
    private String size;
    private Byte updateType;
    private String context;
    private String chanId;
    private String chanName;
    private Integer status;
    private String extra;
    private String projectNameSigle;
    private String origName;
    private List<String> projectName;
    public static AppVO convert(AppBO bo) {
        AppVO dto = new AppVO();

        dto.setAppId(bo.getAppId());
        dto.setChanId(String.valueOf(bo.getChanId()));
        dto.setChanName(bo.getChanName());
        dto.setContext(bo.getContext());
        dto.setExtra(bo.getExtra());
        dto.setPublishTime(bo.getPublishTime());
        dto.setSize(MultiUtil.get2WeiFileSize(bo.getSize(),6)+"M");
        dto.setStatus(bo.getStatus());
        dto.setUpdateType(bo.getUpdateType());
        dto.setUsername(bo.getUsername());
        dto.setVersionName(bo.getVersionname());
//        dto.setProjectNameSigle(bo.getUrl());
        List<String> projectName=new ArrayList<>(2);
        projectName.add(bo.getUrl());
        projectName.add(bo.getOrigName());
        dto.setProjectName(projectName);
//        dto.setOrigName(bo.getOrigName());
        return dto;
    }

    public static List<AppVO> convert(List<AppBO> bos) {
        if(bos==null||bos.size()==0){
            return null;
        }
        // 合并相同的appId
        Map<Integer, AppVO> dtos = new HashMap<>(bos.size());
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
