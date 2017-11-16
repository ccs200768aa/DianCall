package com.diancall.platf.biz.common.log;

import com.diancall.platf.biz.entity.merch.LoginLog;
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

    public static TimerTask loginLog(Integer merchuserid, String ip) {
        return new TimerTask() {
            @Override
            public void run() {
                LoginLog ll = LogFactory.createLoginLog(LogType.LOGIN, merchuserid, null, ip);
//                log
            }
        };
    }
}
