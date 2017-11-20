package com.diancall.core.retcodes;

public enum RetCodeEnum {
    /**
     * ---------------------------商家版会员信息----------------------------------
     */
    MERCH_USER_UNLOGIN(10001001, "用户未登陆"),
    MERCH_USER_ROLE_NOT_EXIST(10001001, "用户角色不够")
    ;

    private int retCode;

    private String retMsg;

    RetCodeEnum(int retCode, String retMsg) {
        this.retCode = retCode;
        this.retMsg = retMsg;
    }

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
