package com.lucian.lucian_blog.utils;

import com.lucian.lucian_blog.bean.bo.UserBO;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    /**
     * 获取登陆用户名
     * @return 登陆名
     */
    public static Integer getCurrentUserId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return ((UserBO)authentication.getDetails()).getId();
        }
        return null;
    }
}
