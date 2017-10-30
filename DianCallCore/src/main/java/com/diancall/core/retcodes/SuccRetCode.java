package com.diancall.core.retcodes;

/**
 * Created with IntelliJ IDEA.
 * Description:操作成功返回码
 * User: Tiki
 * Date: 2017-10-30
 * Time: 16:03
 */
public class SuccRetCode extends RetCodes {

    public SuccRetCode() {
        super.retCode = 200;
        super.retMsg = "操作成功";
    }

}
