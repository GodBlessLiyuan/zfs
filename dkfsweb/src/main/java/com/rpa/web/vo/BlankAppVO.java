package com.rpa.web.vo;

import com.rpa.common.pojo.BlackAppPO;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: xiahui
 * @date: Created in 2019/12/12 18:21
 * @description: 应用黑名单
 * @version: 1.0
 */
@Data
public class BlankAppVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long blankId;
    private String packageName;
    private String appName;
    private Date createTime;
    private String extra;

    public static BlankAppVO convert(BlackAppPO po) {
        BlankAppVO vo = new BlankAppVO();

        vo.setBlankId(po.getBlackId());
        vo.setPackageName(po.getPackageName());
        vo.setAppName(po.getAppName());
        vo.setCreateTime(po.getCreateTime());
        vo.setExtra(po.getExtra());

        return vo;
    }

    public static List<BlankAppVO> convert(List<BlackAppPO> pos) {
        List<BlankAppVO> vos = new ArrayList<>();

        for (BlackAppPO po : pos) {
            vos.add(BlankAppVO.convert(po));
        }

        return vos;
    }
}
