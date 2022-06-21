package com.lucian.back;

import com.lucian.back.manager.UserManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTest {

    @Autowired
    UserManager userManager;

    @Test
    public void test(){
        userManager.getList(null);
    }
}
