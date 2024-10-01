package com.laterya.hearing.model.pojo;

public class Friend {
    private int fromId;
    private int toId;
    private String username;
    private String message;
    private String sex;
    public Friend(int fromId) {
        this.fromId = fromId;
    }

    public Friend(int fromId, int toId, String message) {
        this.fromId = fromId;
        this.toId = toId;
        this.message = message;
    }

    public Friend(int fromId, int toId, String sex, String message) {
        this.fromId = fromId;
        this.toId = toId;
        this.message = message;
        this.sex = sex;
    }

    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public int getToId() {
        return toId;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Friend() {
    }

    public Friend(int fromId, int toId) {
        this.fromId = fromId;
        this.toId = toId;
    }
}
