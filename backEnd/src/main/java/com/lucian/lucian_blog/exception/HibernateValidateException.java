package com.lucian.lucian_blog.exception;

import com.lucian.lucian_blog.common.CommonResult;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class HibernateValidateException {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResult<Map> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        StringBuilder message = new StringBuilder();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            message.append(error.getField()).append(error.getDefaultMessage()).append(",");
        }
        message = new StringBuilder(message.substring(0, message.length() - 1));
        return CommonResult.validateFailed(message.toString());
    }
}
