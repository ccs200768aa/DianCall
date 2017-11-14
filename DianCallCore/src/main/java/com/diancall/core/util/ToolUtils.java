package com.diancall.core.util;

import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Tiki
 * Date: 2017-11-14
 * Time: 16:54
 */
public class ToolUtils {

    /**
     * 获取随机位数的字符串
     *
     * @param length
     * @return
     */
    public static String getRandomString(int length) {
        String baseStr = "abcdefghijklmnopqrstuvwxyz123456";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(baseStr.length());
            sb.append(baseStr.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 判断俩对象是否相等
     *
     * @param obj1
     * @param obj2
     * @return
     */
    public static boolean equls(Object obj1, Object obj2) {
        return obj1 != null ? (obj1.equals(obj2)) : (obj2 == null);
    }

    /**
     * 判断对象是否为空
     *
     * @param o
     * @return
     */
    public static boolean isEmpty(Object o) {
        if (null == o) return true;
        if (o instanceof String) {
            if (o.toString().trim().equals("")) {
                return true;
            }
        } else if (o instanceof List) {
            if (((List) o).size() == 0) return true;
        } else if (o instanceof Set) {
            if (((Set) o).size() == 0) return true;
        } else if (o instanceof Collection) {
            if (((Collection) o).size() == 0) return true;
        } else if (o instanceof Object[]) {
            if (((Object[]) o).length == 0) return true;
        } else if (o instanceof int[]) {
            if (((int[]) o).length == 0) return true;
        } else if (o instanceof long[]) {
            if (((long[]) o).length == 0) return true;
        }
        return false;
    }

    /**
     * 判断对象是否是数字
     *
     * @param obj
     * @return
     */
    public static boolean isNum(Object obj) {
        try {
            Integer.parseInt(obj.toString());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 空取默认值
     *
     * @param o
     * @param defValue
     * @return
     */
    public static Object getValue(Object o, Object defValue) {
        if (isEmpty(o)) {
            return defValue;
        }
        return o;
    }

}
