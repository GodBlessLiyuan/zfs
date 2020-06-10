package com.rpa.common.bo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @author: liyuan
 * @data 2020-06-10 08:53
 */
@Data
public class BuyGiftBO implements Serializable {
    private String userChanName;
    private String saleChanName;//订单Name
    private Integer type;
    private Date createTime;
    private String comTypeName;//商品类型名称
    private String comName;//商品名称
    private Integer days;

}
