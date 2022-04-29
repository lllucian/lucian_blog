package com.lucian.back.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucian.back.service.UserService;
import com.lucian.common.response.CommonResult;
import com.lucian.common.response.ResultCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Component
public class JWTFilter extends OncePerRequestFilter {

    @Autowired
    UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwtToken = request.getHeader("Authorization");
        if (jwtToken == null) {
            jwtToken = "error_jwt";
        }
        try{
            Claims claims = Jwts.parser().setSigningKey("lucian").parseClaimsJws(jwtToken.replace("Bearer",""))
                    .getBody();
            //获取当前登录用户名
            String username = claims.getSubject();
            List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList((String) claims.get("authorities"));
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, null, authorities);
            token.setDetails(userService.loadUserByUsername(username));
            SecurityContextHolder.getContext().setAuthentication(token);
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException exception){
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.write(new ObjectMapper().writeValueAsString(CommonResult.failed(ResultCode.TOKEN_EXPIRED)));
            out.flush();
            out.close();
        } catch (MalformedJwtException exception){
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.write(new ObjectMapper().writeValueAsString(CommonResult.failed(ResultCode.TOKEN_EXPIRED, "token解析错误")));
            out.flush();
            out.close();
        }
    }
}
