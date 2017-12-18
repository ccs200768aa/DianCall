package com.diancall.platf.biz.common;

import com.diancall.core.retcodes.RetCodes;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Tiki
 * Date: 2017-11-15
 * Time: 10:30
 */
public class ReturnObject<T> {

    public ReturnObject(RetCodes retCodes) {
        this.retCodes = retCodes;
    }

    public ReturnObject(RetCodes retCodes, T retObject) {
        this.retCodes = retCodes;
        this.retObject = retObject;
    }

    private RetCodes retCodes;

    private T retObject;

    public RetCodes getRetCodes() {
        return retCodes;
    }

    public void setRetCodes(RetCodes retCodes) {
        this.retCodes = retCodes;
    }

    public T getRetObject() {
        return retObject;
    }

    public void setRetObject(T retObject) {
        this.retObject = retObject;
    }
}
