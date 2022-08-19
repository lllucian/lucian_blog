package com.lucian.back.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.google.common.io.CharStreams;
import com.lucian.back.exception.CaptchaException;
import com.lucian.back.service.CaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author denglingxiang
 * @date 2022/08/18 09:53
 **/
@Service
public class CaptchaServiceImpl implements CaptchaService {

    RedisTemplate redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 检查验证码
     *
     * @param request HttpServletRequest
     */
    @Override
    public void verifyCaptcha(HttpServletRequest request) throws CaptchaException, IOException {
        // login为登陆的url
        if (!Objects.equals(request.getRequestURI(), "/admin/login")) {
            return;
        }
        // 是否为post请求
        if (!request.getMethod().equals(HttpMethod.POST.name())) {
            return;
        }
        JSONObject jsonObject = JSONUtil.parseObj(CharStreams.toString(request.getReader()));
        String code = jsonObject.getStr("captcha");
        if (StrUtil.isBlankIfStr(code)) {
            throw new CaptchaException("验证码为空");
        }
        code = code.toLowerCase();
        String uuidToken = jsonObject.getStr("uuidToken");
        if (StrUtil.isBlankIfStr(uuidToken)) {
            throw new CaptchaException("验证码错误");
        }
        if (redisTemplate.hasKey(uuidToken)) {
            String result = redisTemplate.opsForValue().get(uuidToken).toString();
            if (!code.equals(result)) {
                throw new CaptchaException("验证码错误");
            }
        } else  {
            throw new CaptchaException("验证码错误");
        }

    }

    /**
     * 生成验证码
     *
     * @return 验证码base64
     */
    @Override
    public Map<String, Object> generateCaptcha() {
        UUID uuid = UUID.randomUUID();
        LineCaptcha captcha = CaptchaUtil.createLineCaptcha(200, 80);
        Map<String, Object> objectObjectHashMap = new HashMap<>(2);
        redisTemplate.opsForValue().set(uuid.toString(), captcha.getCode(), Duration.ofMinutes(1));
        objectObjectHashMap.put("uuidToken", uuid.toString());
        objectObjectHashMap.put("code", captcha.getImageBase64Data());
        return objectObjectHashMap;
    }
}
