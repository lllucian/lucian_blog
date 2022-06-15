package com.lucian.common.utils;

import cn.hutool.extra.spring.SpringUtil;
import com.lucian.common.service.UserService;
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
            UserService userService = SpringUtil.getBean(UserService.class);

            return userService.getUserByNickName(authentication.getName()).getId();
        }
        return null;
    }
}
