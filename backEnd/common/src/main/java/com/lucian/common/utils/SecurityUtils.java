package com.lucian.common.utils;

import com.lucian.common.bean.bo.UserBO;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author lingxiangdeng
 */
public class SecurityUtils {

    /**
     * 获取登陆用户名
     * @return 登陆名
     */
    public static String getCurrentUserId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return ((UserBO)authentication.getDetails()).getId();
        }
        return null;
    }
}
