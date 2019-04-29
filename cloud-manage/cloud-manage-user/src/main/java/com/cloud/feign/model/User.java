package com.cloud.feign.model;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 用户信息
 *
 * @author dayun_wang
 */
public class User implements Serializable {

    private String userId;
    private String userName;
    private String passWord;
    private String createTime;
    private String updateTime;

    public User(String userId, String userName, String passWord, String createTime, String updateTime) {
        this.userId = userId;
        this.userName = userName;
        this.passWord = passWord;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
