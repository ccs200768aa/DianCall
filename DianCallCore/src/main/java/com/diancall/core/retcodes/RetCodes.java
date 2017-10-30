package com.diancall.core.retcodes;

/**
 * Created with IntelliJ IDEA.
 * Description: 返回错误码
 * User: Tiki
 * Date: 2017-10-30
 * Time: 16:02
 */
public abstract class RetCodes {
    protected int retCode;
    protected String retMsg;

    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }
}