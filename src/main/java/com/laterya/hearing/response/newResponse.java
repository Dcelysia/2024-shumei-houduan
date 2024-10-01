package com.laterya.hearing.response;

public class newResponse {
    private int errorCode;
    private boolean isError;
    private String errorMsg;
    private boolean message;

    public newResponse(int errorCode, boolean isError, String errorMsg) {
        this.errorCode = errorCode;
        this.isError = isError;
        this.errorMsg = errorMsg;
    }

    public newResponse(int errorCode, boolean isError, String errorMsg, boolean message) {
        this.errorCode = errorCode;
        this.isError = isError;
        this.errorMsg = errorMsg;
        this.message = message;
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

    public boolean isMessage() {
        return message;
    }

    public void setMessage(boolean message) {
        this.message = message;
    }
}
