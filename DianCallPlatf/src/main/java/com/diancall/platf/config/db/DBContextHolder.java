package com.diancall.platf.config.db;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Tiki
 * Date: 2017-11-06
 * Time: 16:07
 */
public class DBContextHolder {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static void setDBType(DBTypeEnum dbTypeEnum) {
        String value = dbTypeEnum.getValue();
        contextHolder.set(value);
    }

    public static String getDBType() {
        return contextHolder.get();
    }

    public static void clearDBType() {
        contextHolder.remove();
    }
}
