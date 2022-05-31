package com.lucian.front;

import com.lucian.front.es_repository.ESPostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author denglingxiang
 * @date 2022/05/26 19:11
 **/
@SpringBootTest
public class EsTest {
    ESPostRepository esPostRepository;

    @Autowired
    public void setEsPostRepository(ESPostRepository esPostRepository) {
        this.esPostRepository = esPostRepository;
    }

    @Test
    public void test() {
        esPostRepository.findAll();
    }
}
