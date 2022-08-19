package com.lucian.back.filter;

import cn.hutool.extra.spring.SpringUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucian.back.service.CaptchaService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author denglingxiang
 * @date 2022/08/18 09:33
 **/
public class CaptchaFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws  ServletException, IOException {
        try{
            CaptchaService captchaService = SpringUtil.getBean(CaptchaService.class);
            captchaService.verifyCaptcha(request);
        } catch (Exception e){
            response.setContentType("application/json;charset=utf-8");
            Map<String, Object> stringObjectHashMap = new HashMap<>(3);
            stringObjectHashMap.put("code", 403);
            stringObjectHashMap.put("message", e.getMessage());
            PrintWriter out = response.getWriter();
            out.write(new ObjectMapper().writeValueAsString(stringObjectHashMap));
            out.flush();
            out.close();
            return;
        }
        filterChain.doFilter(request, response);
    }
}
