package com.diancall.platf.biz.common.shiro;

import com.diancall.core.constant.StringConstant;
import com.diancall.core.util.ToolUtils;
import com.diancall.platf.biz.entity.merch.Merchuser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Tiki
 * Date: 2017-11-14
 * Time: 16:52
 */
public class ShiroHelper {
    //HASH算法循环次数
    private static final int HASHITERATIONS = 1024;

    /**
     * MD5 签名
     *
     * @param credentials 密码
     * @param saltSource  盐
     * @return 加密返回
     */
    public static String md5(String credentials, String saltSource) {
        ByteSource salt = new Md5Hash(saltSource);
        return new SimpleHash(StringConstant.MD5, credentials, salt, HASHITERATIONS).toString();
    }

    /**
     * 获取随机盐值
     *
     * @param length 长度
     * @return
     */
    public static String getRamdomSalt(int length) {
        return ToolUtils.getRandomString(length);
    }

    /**
     * 获取当前Subject
     *
     * @return
     */
    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    /**
     * 认证通过或已记住的用户
     *
     * @return
     */
    public static boolean isUser() {
        return getSubject() != null && getSubject().getPrincipal() != null;
    }

    /**
     * 是否为游客
     *
     * @return
     */
    public static boolean isGuest() {
        return !isUser();
    }

    /**
     * 获取登陆merchuser
     *
     * @return
     */
    public static Merchuser getUser() {
        if (isGuest()) return null;
        return (Merchuser) getSubject().getPrincipals().getPrimaryPrincipal();
    }

    /**
     * 获取session
     *
     * @return
     */
    public static Session getSession() {
        return getSubject().getSession();
    }

    /**
     * 获取指定sessionkey
     *
     * @param key
     * @param <T>
     * @return
     */
    public static <T> T getSessionAttr(String key) {
        Session session = getSession();
        return session != null ? (T) session.getAttribute(key) : null;
    }

    /**
     * 设置shiro指定的sessionKey
     */
    public static void setSessionAttr(String key, Object value) {
        Session session = getSession();
        session.setAttribute(key, value);
    }

    /**
     * 移除shiro指定的sessionKey
     */
    public static void removeSessionAttr(String key) {
        Session session = getSession();
        if (session != null)
            session.removeAttribute(key);
    }
}
