package com.demo.control;

import java.io.Serializable;

/**
 * @Auther: wangyong
 * @Date: 2019/7/3
 * @Description:
 */
public class User implements Serializable {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
