package com.rpa.web.pojo;

/**
 * @author: xiahui
 * @date: Created in 2019/9/24 9:59
 * @description: TODO
 * @version: 1.0
 */
public class User {
    private int user_id;
    private String username;
    private String phone;
    private String ip;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
