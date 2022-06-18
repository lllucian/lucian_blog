package com.lucian.common.utils;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class ServletUtils {

    public static ServletRequestAttributes getRequestAttributes(){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) requestAttributes;
    }

    /**
     * 获取request
     * @return request
     */
    public static HttpServletRequest getRequest(){
        return getRequestAttributes().getRequest();
    }
}

