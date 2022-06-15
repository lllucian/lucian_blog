package com.lucian.back.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author denglingxiang
 * @date 2022/06/15 09:29
 **/
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        Map<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("code", 403);
        stringObjectHashMap.put("message", "权限不足！");
        PrintWriter out = response.getWriter();
        out.write(new ObjectMapper().writeValueAsString(stringObjectHashMap));
        out.flush();
        out.close();
    }
}
