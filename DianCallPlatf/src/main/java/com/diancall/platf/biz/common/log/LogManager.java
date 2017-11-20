package com.diancall.platf.biz.common.log;

import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Tiki
 * Date: 2017-11-16
 * Time: 20:00
 */
public class LogManager {
    private final int OPERATE_DELAY_TIME = 10;

    private ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(10);

    private LogManager() {
    }

    private static LogManager logManager = new LogManager();

    public static LogManager me() {
        return logManager;
    }

    public void excuteLog(TimerTask task) {
        executor.schedule(task, OPERATE_DELAY_TIME, TimeUnit.MILLISECONDS);
    }
}
