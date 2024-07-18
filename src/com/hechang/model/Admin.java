package com.hechang.model;

import java.util.List;

public class Admin extends Account{
    private String password; //用户密码
    private String phone;    //手机号码
    private int level;  //权限等级

    public Admin() {
    }

    public Admin(String phone, int level) {
        this.phone = phone;
        this.level = level;
    }

    public Admin(long id, String name, String password, String age, String sex, String phone, int level) {
        super(id, name, age, sex);
        this.phone = phone;
        this.level = level;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return super.toString() + "Admin{" +
                "phone='" + phone + '\'' +
                ", level=" + level +
                '}';
    }
}
