package com.zfs.web.vo;

import com.zfs.common.pojo.WxsupportPO;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: xiahui
 * @date: Created in 2019/9/30 11:10
 * @description: 微信白名单
 * @version: 1.0
 */
@Data
public class WxSupportVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer wId;
    private String packageName;
    private Date createTime;
    private String extra;

    public static WxSupportVO convert(WxsupportPO po) {
        WxSupportVO vo = new WxSupportVO();

        vo.setWId(po.getwId());
        vo.setPackageName(po.getPackageName());
        vo.setCreateTime(po.getCreateTime());
        vo.setExtra(po.getExtra());

        return vo;
    }

    public static List<WxSupportVO> convert(List<WxsupportPO> pos) {
        List<WxSupportVO> vos = new ArrayList<>();

        for (WxsupportPO po : pos) {
            vos.add(WxSupportVO.convert(po));
        }

        return vos;
    }
}
