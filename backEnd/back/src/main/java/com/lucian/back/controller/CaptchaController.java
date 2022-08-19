package com.lucian.back.controller;

import com.lucian.back.service.CaptchaService;
import com.lucian.common.response.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author denglingxiang
 * @date 2022/08/18 15:34
 **/
@RestController
@RequestMapping("captcha")
public class CaptchaController {

    CaptchaService captchaService;

    @Autowired
    public void setCaptchaService(CaptchaService captchaService) {
        this.captchaService = captchaService;
    }

    /**
     * 获取验证码
     * @return 返回验证码信息
     */
    @GetMapping("getImageCaptcha")
    public CommonResult<Map<String, Object>> imageCaptcha(){
        return CommonResult.successNoMessage(captchaService.generateCaptcha());
    }
}
