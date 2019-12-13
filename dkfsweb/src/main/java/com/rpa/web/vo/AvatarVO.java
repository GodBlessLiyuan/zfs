package com.rpa.web.vo;

import com.rpa.common.bo.AvatarBO;
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

    public static AvatarVO convert(AvatarBO bo) {
        AvatarVO vo = new AvatarVO();

        vo.setAvatarId(bo.getAvatarId());
        vo.setName(bo.getVersionName() + "|" + bo.getChanName());
        vo.setIds(bo.getAvatarId() + "|" + bo.getChanId());
        vo.setContext(bo.getContext());
        vo.setExtra(bo.getExtra());
        vo.setPublishTime(bo.getPublishTime());
        vo.setSize(bo.getSize());
        vo.setStatus(bo.getStatus());
        vo.setUpdateType(bo.getUpdateType());
        vo.setUsername(bo.getUsername());
        vo.setVersionName(bo.getVersionName());

        return vo;
    }

    public static List<AvatarVO> convert(List<AvatarBO> bos) {
        // 合并相同的 avatarId
        Map<Long, AvatarVO> map = new HashMap<>();
        for (AvatarBO bo : bos) {
            long avatarId = bo.getAvatarId();
            if (map.containsKey(avatarId)) {
                AvatarVO dto = map.get(avatarId);
                dto.setName(dto.getName() + "," + bo.getVersionName() + "|" + bo.getChanName());
                dto.setIds(dto.getIds() + "," + bo.getAvatarId() + "|" + bo.getChanId());
            } else {
                map.put(avatarId, AvatarVO.convert(bo));
            }
        }

        return new ArrayList<>(map.values());
    }
}
