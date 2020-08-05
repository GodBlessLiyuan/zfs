package com.rpa.web.vo;

import com.rpa.common.pojo.SoftChannelPO;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: xiahui
 * @date: Created in 2019/9/29 8:48
 * @description: 渠道信息
 * @version: 1.0
 */
@Data
public class SoftChannelVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer softChannelId;
    private String name;
    private String extra;
    private Date createTime;
    private Date updateTime;

    public static SoftChannelVO convert(SoftChannelPO po) {
        SoftChannelVO vo = new SoftChannelVO();

        vo.setSoftChannelId(po.getSoftChannelId());
        vo.setName(po.getName());
        vo.setExtra(po.getExtra());
        vo.setCreateTime(po.getCreateTime());
        vo.setUpdateTime(po.getUpdateTime());

        return vo;
    }

    public static List<SoftChannelVO> convert(List<SoftChannelPO> pos) {
        List<SoftChannelVO> vos = new ArrayList<>();

        for (SoftChannelPO po : pos) {
            vos.add(SoftChannelVO.convert(po));
        }

        return vos;
    }
}
