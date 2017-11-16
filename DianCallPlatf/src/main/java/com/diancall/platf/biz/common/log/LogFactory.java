package com.diancall.platf.biz.common.log;

import com.diancall.platf.biz.entity.merch.LoginLog;
import com.diancall.platf.biz.entity.merch.OperationLog;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Tiki
 * Date: 2017-11-16
 * Time: 20:19
 */
public class LogFactory {

    public static LoginLog createLoginLog(LogType logType, Integer merchuserid, String msg, String ip) {
        LoginLog loginLog = new LoginLog();
        loginLog.setLogname(logType.getMessage());
        loginLog.setCreatetime(new Date());
        loginLog.setIp(ip);
        loginLog.setSucceed(LogResult.LOG_SUCC.getMessage());
        loginLog.setMerchuserid(merchuserid);
        loginLog.setMessage(msg);
        return loginLog;
    }

    public OperationLog createOperationLog(LogType logType, Integer merchuserid,
                                           String msg, String bussinessName, String clazzName, String methodName, LogResult logResult) {
        OperationLog ol = new OperationLog();
        ol.setLogname(bussinessName);
        ol.setLogtype(logType.getMessage());
        ol.setCreatetime(new Date());
        ol.setMerchuserid(merchuserid);
        ol.setMessage(msg);
        ol.setMethod(methodName);
        ol.setSucceed(logResult.getMessage());
        return ol;
    }
}
