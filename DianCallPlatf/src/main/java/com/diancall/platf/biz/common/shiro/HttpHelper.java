package com.diancall.platf.biz.common.shiro;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Tiki
 * Date: 2017-11-17
 * Time: 15:51
 */
public class HttpHelper {

    public static HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest();
        return request;
    }

    public static String getIp() {
        return getRequest().getRemoteHost();
    }
}
