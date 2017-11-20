package com.diancall.platf.biz.common.log;

import com.diancall.platf.biz.common.DBHelper;
import com.diancall.platf.biz.dao.merch.LoginLogMapper;
import com.diancall.platf.biz.dao.merch.OperationLogMapper;
import com.diancall.platf.biz.entity.merch.LoginLog;
import com.diancall.platf.biz.entity.merch.OperationLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Tiki
 * Date: 2017-11-16
 * Time: 20:11
 */
public class LogTaskFactory {


    private static final Logger logger = LoggerFactory.getLogger(LogTaskFactory.class);
    private static LoginLogMapper loginLogMapper = DBHelper.getMapper(LoginLogMapper.class);
    private static OperationLogMapper operationLogMapper = DBHelper.getMapper(OperationLogMapper.class);


    public static TimerTask loginLog(Integer merchuserid, String ip) {
        return new TimerTask() {
            @Override
            public void run() {
                LoginLog ll = LogFactory.createLoginLog(LogType.LOGIN, merchuserid, null, ip);
                try {
                    loginLogMapper.insert(ll);
                } catch (Exception e) {
                    logger.error("创建登陆日志异常,{}",e.getMessage());
                }
            }
        };
    }

    public static TimerTask loginFailLog(Integer merchuserid, String msg, String ip) {
        return new TimerTask() {
            @Override
            public void run() {
                LoginLog ll = LogFactory.createLoginLog(LogType.LOGIN_FALI, merchuserid, msg, ip);
                try {
                    loginLogMapper.insert(ll);
                } catch (Exception e) {
                    logger.error("创建登陆失败日志异常");
                }
            }
        };
    }

    public static TimerTask logoutLog(int merchuserid, String ip) {
        return new TimerTask() {
            @Override
            public void run() {
                LoginLog ll = LogFactory.createLoginLog(LogType.LOGOUT, merchuserid, null, ip);
                try {
                    loginLogMapper.insert(ll);
                } catch (Exception e) {
                    logger.error("创建登出日志失败");
                }
            }
        };
    }

    public static TimerTask bussinessLog(int merchuserid, String msg, String bussinessName, String clazzName, String methodName) {
        return new TimerTask() {
            @Override
            public void run() {
                OperationLog ol = LogFactory.createOperationLog(LogType.BUSSINESS, merchuserid, msg, bussinessName, clazzName, methodName, LogResult.LOG_SUCC);
                try {
                    operationLogMapper.insert(ol);
                } catch (Exception e) {
                    logger.error("创建业务操作日志失败");
                }
            }
        };
    }
}
