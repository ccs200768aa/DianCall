package com.diancall.platf.biz.common.log;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Tiki
 * Date: 2017-11-16
 * Time: 20:42
 */
public enum LogResult {
    LOG_SUCC("成功"),
    LOG_FAIL("失败");

    private String message;

    LogResult(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
