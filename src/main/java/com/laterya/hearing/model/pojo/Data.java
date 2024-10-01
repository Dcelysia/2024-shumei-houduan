package com.laterya.hearing.model.pojo;

public class Data {
    private String time;
    private String mileage;
    private String type;
    private int Id;
    private int DataId;
    private String username;
    private User user;
    private String location;
    private String bytes;
    private String password;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBytes() {
        return bytes;
    }

    public void setBytes(String bytes) {
        this.bytes = bytes;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getDataId() {
        return DataId;
    }

    @Override
    public String toString() {
        return "Data{" +
                "time='" + time + '\'' +
                ", mileage='" + mileage + '\'' +
                ", type='" + type + '\'' +
                ", Id=" + Id +
                ", username='" + username + '\'' +
                ", password='" + username + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    public Data() {
    }

    public void setDataId(int dataId) {
        this.DataId = dataId;
    }


    public Data(String time, String mileage, String type, int id) {
        this.time = time;
        this.mileage = mileage;
        this.type = type;
        Id = id;
    }

    public Data(String time, String mileage, String type, int id, String location) {
        this.time = time;
        this.mileage = mileage;
        this.type = type;
        Id = id;
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}


