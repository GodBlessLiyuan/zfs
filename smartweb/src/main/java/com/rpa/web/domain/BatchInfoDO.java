package com.rpa.web.domain;

import com.rpa.web.pojo.BatchInfoPO;

import java.util.Date;

/**
 * @author: dangyi
 * @date: Created in 10:31 2019/10/9
 * @version: 1.0.0
 * @description:
 */
public class BatchInfoDO extends BatchInfoPO {

    private String chanNickname;

    private String chanName;

    private String comTypeName;

    private Date createTime;

    /**
     * 用户渠道
     */
    private String userChanName;
    
    public String getChanNickname() {
        return chanNickname;
    }

    
    public void setChanNickname(String chanNickname) {
        this.chanNickname = chanNickname;
    }

    
    public String getChanName() {
        return chanName;
    }

    
    public void setChanName(String chanName) {
        this.chanName = chanName;
    }

    
    public String getComTypeName() {
        return comTypeName;
    }

    
    public void setComTypeName(String comTypeName) {
        this.comTypeName = comTypeName;
    }

    
    public Date getCreateTime() {
        return createTime;
    }

    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUserChanName() {
        return userChanName;
    }

    public void setUserChanName(String userChanName) {
        this.userChanName = userChanName;
    }
}
