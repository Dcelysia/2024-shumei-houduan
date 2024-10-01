package com.laterya.hearing.model.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private String sex;
    private String phone;
    private String emergencyNumber;
    @TableField(exist = false)
    private String new_Psw;
    @TableField(exist = false)
    private String new_Sex;
    @TableField(exist = false)
    private String new_Usn;
    private String bytes;

    public String getBytes() {
        return bytes;
    }

    public void setBytes(String bytes) {
        this.bytes = bytes;
    }

    public String getNew_Usn() {
        return new_Usn;
    }

    public void setNew_Usn(String new_Usn) {
        this.new_Usn = new_Usn;
    }

    public String getNew_Sex() {
        return new_Sex;
    }

    public void setNew_Sex(String new_Sex) {
        this.new_Sex = new_Sex;
    }

    public String getNew_Psw() {
        return new_Psw;
    }

    public void setNew_Psw(String new_Psw) {
        this.new_Psw = new_Psw;
    }

    public User() {
    }

    public User(Long id, String username, String password, String sex) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.sex = sex;
    }

    public User(String username, String password, String sex) {
        this.username = username;
        this.password = password;
        this.sex = sex;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

}
