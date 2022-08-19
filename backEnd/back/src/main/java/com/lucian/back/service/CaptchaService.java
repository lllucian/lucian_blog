package com.lucian.back.service;

import com.lucian.back.exception.CaptchaException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * @author denglingxiang
 * @date 2022/08/18 09:49
 **/
public interface CaptchaService {
    /**
     * 检查验证码
     * @throws CaptchaException 验证码异常
     * @param request HttpServletRequest
     */
    void verifyCaptcha(HttpServletRequest request) throws CaptchaException, IOException;

    /**
     * 生成验证码
     * @return 验证码base64
     */
    Map<String, Object> generateCaptcha();
}
