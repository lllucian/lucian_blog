package com.lucian.lucian_blog.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucian.lucian_blog.common.CommonResult;
import com.lucian.lucian_blog.common.ResultCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class JWTFilter extends GenericFilter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String jwtToken = request.getHeader("Authorization");
        try{
            Claims claims = Jwts.parser().setSigningKey("lucian").parseClaimsJws(jwtToken.replace("Bearer",""))
                    .getBody();
            String username = claims.getSubject();//获取当前登录用户名
            List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList((String) claims.get("authorities"));
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(token);
            filterChain.doFilter(request, servletResponse);
        } catch (ExpiredJwtException exception){
            servletResponse.setContentType("application/json;charset=utf-8");
            PrintWriter out = servletResponse.getWriter();
            out.write(new ObjectMapper().writeValueAsString(CommonResult.failed(ResultCode.TOKEN_EXPIRED)));
            out.flush();
            out.close();
        } catch (Exception exception){
            servletResponse.setContentType("application/json;charset=utf-8");
            PrintWriter out = servletResponse.getWriter();
            out.write(new ObjectMapper().writeValueAsString(CommonResult.failed(ResultCode.TOKEN_EXPIRED, "token解析错误")));
            out.flush();
            out.close();
        }
    }
}
