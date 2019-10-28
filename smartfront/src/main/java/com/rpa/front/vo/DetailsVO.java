package com.rpa.front.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author: xiahui
 * @date: Created in 2019/10/28 20:44
 * @description: 邀请详情
 * @version: 1.0
 */
@Data
public class DetailsVO extends IncomeVO {

    private List<Detail> details;

    @Data
    public class Detail {
        private String ph;
        private Date ctime;
        private Long earnings;
    }
}
