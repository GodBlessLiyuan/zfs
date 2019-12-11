package com.rpa.web.vo;

import com.rpa.common.bo.WhiteDeviceBO;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: xiahui
 * @date: Created in 2019/9/30 14:22
 * @description: 测试白名单
 * @version: 1.0
 */
@Data
public class WhiteDeviceVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long deviceId;
    private Date createTime;
    private String imei;
    private String extra;

    /**
     * do 转 dtos
     *
     * @param bo
     * @return
     */
    public static WhiteDeviceVO convert(WhiteDeviceBO bo) {
        WhiteDeviceVO vo = new WhiteDeviceVO();

        vo.setDeviceId(bo.getDeviceId());
        vo.setExtra(bo.getExtra());
        vo.setImei(bo.getImei());
        vo.setCreateTime(bo.getCreateTime());

        return vo;
    }

    /**
     * dos 转 dtos
     *
     * @param bos
     * @return
     */
    public static List<WhiteDeviceVO> convert(List<WhiteDeviceBO> bos) {
        List<WhiteDeviceVO> vos = new ArrayList<>();

        for (WhiteDeviceBO bo : bos) {
            vos.add(WhiteDeviceVO.convert(bo));
        }

        return vos;
    }
}
