package com.stylefeng.guns.api.vo;

import java.io.Serializable;

/**
 * Created by root on 19-3-21.
 */
public class UserModel implements Serializable{
    //zzy 因为序列化没加调了1年
    private String username;
    private String password;
    private String email;
    private String phone;
    private String address;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
