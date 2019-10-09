package com.rpa.web.domain;

import com.rpa.web.pojo.BatchInfoPO;

import java.util.Date;

/**
 * @author: dangyi
 * @date: Created in 10:31 2019/10/9
 * @version: 1.0.0
 * @description: TODO
 */
public class BatchInfoDO extends BatchInfoPO {

    private String chanNickname;

    private String chanName;

    private String comTypeName;

    private Date createTime;

    @Override
    public String getChanNickname() {
        return chanNickname;
    }

    @Override
    public void setChanNickname(String chanNickname) {
        this.chanNickname = chanNickname;
    }

    @Override
    public String getChanName() {
        return chanName;
    }

    @Override
    public void setChanName(String chanName) {
        this.chanName = chanName;
    }

    @Override
    public String getComTypeName() {
        return comTypeName;
    }

    @Override
    public void setComTypeName(String comTypeName) {
        this.comTypeName = comTypeName;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
