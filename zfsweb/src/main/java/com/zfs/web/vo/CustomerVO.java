package com.zfs.web.vo;

import com.zfs.common.pojo.ServiceNumberPO;
import com.zfs.common.utils.DateUtilCard;
import lombok.Data;

import java.util.ArrayList;

import java.util.List;

/**
 * @Description:
 * @author: liyuan
 * @data 2020-06-17 20:40
 */
@Data
public class CustomerVO {
    private String operator;
    private  String number;//账号
    private String startDate;//
    private String endDate;//更新时间
    private Long svnmID;
    private Byte type;
    public static List<CustomerVO> convert(List<ServiceNumberPO> pos) {
        List<CustomerVO> list=new ArrayList<>();
        for(ServiceNumberPO po:pos){
            CustomerVO dto=new CustomerVO();
            dto.setOperator(po.getUsername());
            dto.setNumber(po.getKf());//客服账号
            dto.setStartDate(DateUtilCard.date2Str(po.getCreateTime(),DateUtilCard.YMD_HMS));
            dto.setSvnmID(po.getSvnmId());
            dto.setType(po.getType());
            list.add(dto);
        }
        return list;
    }
}
