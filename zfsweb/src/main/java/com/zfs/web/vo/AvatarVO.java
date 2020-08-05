package com.zfs.web.vo;

import com.zfs.common.bo.AvatarBO;
import lombok.Data;

import java.io.Serializable;
import java.util.*;

/**
 * @author: xiahui
 * @date: Created in 2019/12/12 20:19
 * @description: 分身更新
 * @version: 1.0
 */
@Data
public class AvatarVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long avatarId;
    private Date publishTime;
    private String username;
    private String versionName;
    private Integer size;
    private Byte updateType;
    private String context;
    // 应用Id和渠道Id组合
    private String ids;
    private String name;
    private Byte status;
    private String extra;
    private String osVersion;
    public static AvatarVO convert(AvatarBO bo) {
        AvatarVO vo = new AvatarVO();

        vo.setAvatarId(bo.getAvatarId());
        vo.setName(bo.getAppVersionName() + ":" + bo.getChanName());
        vo.setIds(bo.getAppId() + "|" + bo.getChanId());
        vo.setContext(bo.getContext());
        vo.setExtra(bo.getExtra());
        vo.setPublishTime(bo.getPublishTime());
        vo.setSize(bo.getSize());
        vo.setStatus(bo.getStatus());
        vo.setUpdateType(bo.getUpdateType());
        vo.setUsername(bo.getUsername());
        vo.setVersionName(bo.getVersionName());
        if(bo.getOsVersion()==null||bo.getOsVersion()==0){
            vo.setOsVersion("普通分身");
        }else {
            vo.setOsVersion("安卓10分身");
        }

        return vo;
    }

    public static List<AvatarVO> convert(List<AvatarBO> bos) {
        Map<Long, Map<String, String>> map = new HashMap<>();
        for (AvatarBO bo : bos) {
            Long avatarId = bo.getAvatarId();
            if (map.containsKey(avatarId)) {
                Map<String, String> sMap = map.get(avatarId);
                String appName = bo.getAppVersionName();
                if (sMap.containsKey(appName)) {
                    sMap.put(appName, sMap.get(appName) + "," + bo.getChanName());
                } else {
                    sMap.put(appName, appName + ":" + bo.getChanName());
                }
            } else {
                Map<String, String> sMap = new HashMap<>();
                sMap.put(bo.getAppVersionName(), bo.getAppVersionName()+ ":" + bo.getChanName());
                map.put(avatarId, sMap);
            }
        }

        Map<Long, String> nameMap = new HashMap<>();
        for(Map.Entry<Long, Map<String, String>> entry: map.entrySet()) {
            StringBuilder sb = new StringBuilder();
            sb.append("|");
            for(Map.Entry<String, String> e: entry.getValue().entrySet()) {
                sb.append(e.getValue());
                sb.append("|");
            }
            nameMap.put(entry.getKey(), sb.toString());
        }

        // 合并相同的 avatarId
        Map<Long, AvatarVO> res = new HashMap<>();
        for (AvatarBO bo : bos) {
            long avatarId = bo.getAvatarId();
            if (res.containsKey(avatarId)) {
                AvatarVO vo = res.get(avatarId);
                vo.setIds(vo.getIds() + "," + bo.getAppId() + "|" + bo.getChanId());
            } else {
                AvatarVO vo = AvatarVO.convert(bo);
                vo.setName(nameMap.get(avatarId));
                res.put(avatarId, vo);
            }
        }

        return new ArrayList<>(res.values());
    }
}
