package com.lucian.back.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucian.common.bean.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lingxiangdeng
 */
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {
    private final boolean postOnly = true;

    protected JWTLoginFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager){
        super(new AntPathRequestMatcher(defaultFilterProcessesUrl));
        setAuthenticationManager(authenticationManager);
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse resp) throws AuthenticationException, IOException, ServletException {
        if (this.postOnly && !req.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + req.getMethod());
        }
        User user = new ObjectMapper().readValue(req.getInputStream(), User.class);
        return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
    }
    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse resp, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();
        StringBuffer as = new StringBuffer();
        for (GrantedAuthority authority : authorities) {
            as.append(authority.getAuthority())
                    .append(",");
        }
        String jwt = Jwts.builder()
                //配置用户角色
                .claim("authorities", as)
                .setSubject(authResult.getName())
                .setExpiration(new Date(System.currentTimeMillis() + 30 *  60 * 1000))
                .signWith(SignatureAlgorithm.HS512,"lucian")
                .compact();
        resp.setContentType("application/json;charset=utf-8");
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("jwtToken", jwt);
        map.put("message", "登陆成功！");
        PrintWriter out = resp.getWriter();
        out.write(new ObjectMapper().writeValueAsString(map));
        out.flush();
        out.close();
    }
    protected void unsuccessfulAuthentication(HttpServletRequest req, HttpServletResponse resp, AuthenticationException failed) throws IOException, ServletException {
        resp.setContentType("application/json;charset=utf-8");
        Map<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("code", 401);
        stringObjectHashMap.put("message", "登陆失败！");
        PrintWriter out = resp.getWriter();
        out.write(new ObjectMapper().writeValueAsString(stringObjectHashMap));
        out.flush();
        out.close();
    }
}
