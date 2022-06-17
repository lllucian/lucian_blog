package com.lucian.back.validation;

import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lucian.back.annotation.Unique;
import lombok.SneakyThrows;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author denglingxiang
 * @date 2022/06/13 09:03
 **/
public class RepeatValidateImpl implements ConstraintValidator<Unique, Object> {


    private String[] fields;

    private String message;


    private Class<? extends BaseMapper<?>> baseMapper;

    @Override
    public void initialize(Unique repeatValidate) {
        this.fields = repeatValidate.fields();
        this.message = repeatValidate.message();
        this.baseMapper = repeatValidate.baseMapper();
    }

    @SneakyThrows
    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {
        RepeatValidateUtils bean = SpringUtil.getBean(RepeatValidateUtils.class);
        boolean valid = bean.fieldRepeat(fields, message, object, baseMapper);
        if (!valid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate()).addPropertyNode(fields[0]).addConstraintViolation();
        }
        return valid;
    }
}
