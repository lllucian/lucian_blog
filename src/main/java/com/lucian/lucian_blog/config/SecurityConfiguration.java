package com.lucian.lucian_blog.config;

import com.lucian.lucian_blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    UserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests(authorize -> authorize
//                .anyRequest().authenticated().withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
//                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
//                        o.setAccessDecisionManager(accessDecisionManager);
//                        o.setSecurityMetadataSource(roleFilter);
//                        return o;
//                    }
//                })).formLogin(AbstractAuthenticationFilterConfigurer::permitAll);
        // 简化配置
        http.authorizeRequests(authorize -> authorize.antMatchers("/admin/**").hasRole("admin"))
                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll).csrf(AbstractHttpConfigurer::disable);
    }
}
