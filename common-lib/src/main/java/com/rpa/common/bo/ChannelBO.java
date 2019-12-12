package com.rpa.common.bo;

import com.rpa.common.pojo.ChannelPO;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * t_channel
 * @author 
 */
@Data
public class ChannelBO extends ChannelPO {
    private String proName;
    private String phone;
}