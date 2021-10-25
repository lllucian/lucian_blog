package com.lucian.lucian_blog.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createdAt", Date.class, new Date()); // 起始版本 3.3.3(推荐)
        this.strictInsertFill(metaObject, "updatedAt", Date.class, new Date()); // 起始版本 3.3.3(推荐)
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updatedAt", Date.class, new Date()); // 起始版本 3.3.3(推荐)
    }
}
