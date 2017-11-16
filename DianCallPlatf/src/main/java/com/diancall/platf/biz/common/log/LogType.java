package com.diancall.platf.biz.common.log;

public enum LogType {
    LOGIN("登陆日志"),
    LOGIN_FALI("登陆失败日志"),
    LOGOUT("退出登陆"),
    EXCEPTION("异常日志"),
    BUSSINESS("业务日志");

    String message;

    LogType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
