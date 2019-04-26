package com.cloud.core.model;

import java.io.Serializable;
import java.util.UUID;

/**
 * 用户
 *
 * @author :dayun_wang
 */
public class User implements Serializable {
    private String id;
    private String userName;
    private String passWord;
    private String headImge;
    private String address;
    private String crateTime;
    private String updateTime;
    private String lastTime;

    public User(String id, String userName, String passWord, String headImge, String address, String crateTime, String updateTime, String lastTime) {
        this.id = UUID.randomUUID().toString().replaceAll("-","");
        this.userName = userName;
        this.passWord = passWord;
        this.headImge = headImge;
        this.address = address;
        this.crateTime = crateTime;
        this.updateTime = updateTime;
        this.lastTime = lastTime;
    }
}
