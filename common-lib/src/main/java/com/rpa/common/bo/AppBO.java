package com.rpa.common.bo;

import com.rpa.common.pojo.AppPO;
import lombok.Data;

/**
 * @author: xiahui
 * @date: Created in 2019/12/11 10:54
 * @description: TODO
 * @version: 1.0
 */
@Data
public class AppBO extends AppPO {
    private String username;
    private int chanId;
    private String chanName;
}
