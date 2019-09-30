package com.rpa.web.dto;

import com.rpa.web.pojo.WxSupportPO;

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
public class WxSupportDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer wId;
    private String packageName;
    private Date createTime;
    private String extra;

    public Integer getwId() {
        return wId;
    }

    public void setwId(Integer wId) {
        this.wId = wId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    /**
     * po 转 dto
     *
     * @param po
     * @return
     */
    public static WxSupportDTO convert(WxSupportPO po) {
        WxSupportDTO dto = new WxSupportDTO();

        dto.setwId(po.getwId());
        dto.setPackageName(po.getPackageName());
        dto.setCreateTime(po.getCreateTime());
        dto.setExtra(po.getExtra());

        return dto;
    }

    /**
     * pos 转 dtos
     *
     * @param pos
     * @return
     */
    public static List<WxSupportDTO> convert(List<WxSupportPO> pos) {
        List<WxSupportDTO> dtos = new ArrayList<>();

        for (WxSupportPO po : pos) {
            dtos.add(WxSupportDTO.convert(po));
        }

        return dtos;
    }
}
