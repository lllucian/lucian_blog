package com.lucian.back.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author denglingxiang
 * @date 2022/07/13 16:52
 **/
public class JwtLoginFailedEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        Map<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("code", 401);
        stringObjectHashMap.put("message", "登陆失败！");
        PrintWriter out = response.getWriter();
        out.write(new ObjectMapper().writeValueAsString(stringObjectHashMap));
        out.flush();
        out.close();
    }
}
