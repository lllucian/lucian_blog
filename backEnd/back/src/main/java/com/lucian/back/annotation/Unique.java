package com.lucian.back.annotation;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lucian.back.validation.RepeatValidateImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RepeatValidateImpl.class)
public @interface Unique {

    /**
     * 错误文言
     * @return 错误文言
     */
    String message() default "已存在";

    /**
     * 检查唯一性的字段
     * @return 字段集合
     */
    String[] fields() default {};

    Class<? extends BaseMapper<?>> baseMapper();

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    @Target({ ElementType.TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        Unique[] value();
    }
}
