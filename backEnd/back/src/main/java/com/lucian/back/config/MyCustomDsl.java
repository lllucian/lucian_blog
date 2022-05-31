package com.lucian.back.config;

import com.lucian.back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.lucian.back.config.JWTFilter.jwtFilter;

/**
 * @author lingxiangdeng
 */
public class MyCustomDsl extends AbstractHttpConfigurer<MyCustomDsl, HttpSecurity> {

    UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    //@Override
    //protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //    auth.userDetailsService(userService);
    //}


    @Override
    public void configure(HttpSecurity http) throws Exception {
        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
        http.addFilterBefore(new JWTLoginFilter("/login", authenticationManager),
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    public static MyCustomDsl customDsl() {
        return new MyCustomDsl();
    }
}
