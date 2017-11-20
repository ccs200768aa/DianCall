package com.diancall.core.retcodes;

/**
 * Created with IntelliJ IDEA.
 * Description:错误码详情
 * User: Tiki
 * Date: 2017-10-30
 * Time: 16:04
 */
public class ErrRetCode extends RetCodes {

    public ErrRetCode(int retCode, String retMsg) {
        super();
        this.retCode = retCode;
        this.retMsg = retMsg;
    }

    public ErrRetCode(RetCodeEnum retCodeEnum) {
        super();
        this.retCode = retCodeEnum.getRetCode();
        this.retMsg = retCodeEnum.getRetMsg();
    }
}
