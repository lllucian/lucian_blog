package com.lucian.back;

import com.lucian.back.form_parm.UserParam;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * @author denglingxiang
 * @date 2022/06/17 14:39
 **/
@SpringBootTest
public class UniqueValidateTest {

    static Validator validator;

    @BeforeAll
    public static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


    @Test
    public void test(){
        UserParam userParam = new UserParam();
        userParam.setUsername("lucian");
        userParam.setEmail("1024462231@qq.com");
        Set<ConstraintViolation<UserParam>> validate = validator.validate(userParam);
        System.out.println(validate.size());
    }
}
