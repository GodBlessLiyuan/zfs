package com.zfs.web.dto;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;

/**
 * @Description:
 * @author: liyuan
 * @data 2020-08-14 14:45
 */
@Data
public class NoticeDTO implements Serializable {
     private Byte type;
     private String text;
     private String avatar;
     private String title;
     private String url;
     private String[] effectivetime;
     private String[] notificationperiod;
     private Integer[] menbers;
}
