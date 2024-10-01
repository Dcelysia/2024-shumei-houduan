package com.laterya.hearing.response;

import cn.hutool.json.JSON;
import com.laterya.hearing.model.pojo.User;

import java.util.List;
import java.util.Set;

public class Response {

    private int errorCode;
    private boolean isError;
    private String errorMsg;
    private User user;
    private List list;
    private Set set;
    private JSON picture;
    private String message;


    public Response() {
    }

    public JSON getPicture() {
        return picture;
    }
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPicture(JSON picture) {
        this.picture = picture;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public Set getSet() {
        return set;
    }

    public void setSet(Set set) {
        this.set = set;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Response(int errorCode, boolean isError, String errorMsg) {
        this.errorCode = errorCode;
        this.isError = isError;
        this.errorMsg = errorMsg;
    }

    public Response(int errorCode, boolean isError, String errorMsg, User user) {
        this.errorCode = errorCode;
        this.isError = isError;
        this.errorMsg = errorMsg;
        this.user = user;
    }
    public Response(int errorCode, boolean isError, String errorMsg, List list) {
        this.errorCode = errorCode;
        this.isError = isError;
        this.errorMsg = errorMsg;
        this.list=list;
    }
    public Response(int errorCode, boolean isError, String errorMsg, Set set) {
        this.errorCode = errorCode;
        this.isError = isError;
        this.errorMsg = errorMsg;
        this.set=set;
    }

    public Response(int errorCode, boolean isError, String errorMsg, String message) {
        this.errorCode = errorCode;
        this.isError = isError;
        this.errorMsg = errorMsg;
        this.message=message;
    }
    public Response(int errorCode, boolean isError, String errorMsg,List list,JSON picture) {
        this.errorCode = errorCode;
        this.isError = isError;
        this.list=list;
        this.picture=picture;
    }

}
