package com.lucian.back.config;

import com.lucian.back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * spring security配置
 * @author lingxiangdeng
 */
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Resource
    JWTFilter jwtFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 放行swagger资源
        web.ignoring().antMatchers("/swagger-**/**", "/v3/api-docs");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 简化配置
        http.authorizeRequests(authorize -> authorize.antMatchers("/admin/**").hasRole("admin"))
                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
                .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .cors().and().csrf().disable();
    }
}
