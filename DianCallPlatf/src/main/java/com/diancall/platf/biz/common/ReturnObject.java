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

    public ReturnObject(RetCodes retCodes, T t) {
        this.retCodes = retCodes;
        this.retObject = t;
    }

    private RetCodes retCodes;

    private T retObject;

    public RetCodes getRetCodes() {
        return retCodes;
    }

    public void setRetCodes(RetCodes retCodes) {
        this.retCodes = retCodes;
    }

    public T getT() {
        return retObject;
    }

    public void setT(T retObject) {
        this.retObject = retObject;
    }
}
