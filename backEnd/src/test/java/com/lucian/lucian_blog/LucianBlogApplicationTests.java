package com.lucian.lucian_blog;

import com.lucian.lucian_blog.bean.entity.Category;
import com.lucian.lucian_blog.bean.entity.Post;
import com.lucian.lucian_blog.bean.entity.PostCategory;
import com.lucian.lucian_blog.dao.CategoryDao;
import com.lucian.lucian_blog.dao.PostCategoryDao;
import com.lucian.lucian_blog.dao.PostDao;
import com.lucian.lucian_blog.utils.MinIoUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.*;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class LucianBlogApplicationTests {

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    PostDao postDao;

    @Autowired
    PostCategoryDao postCategoryDao;

    @Test
    void contextLoads() {
    }

    @Test
    void generalUserPassword(){
        String idForEncode = "bcrypt";
        Map encoders = new HashMap<>();
        encoders.put(idForEncode, new BCryptPasswordEncoder());
        encoders.put("noop", NoOpPasswordEncoder.getInstance());
        encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
        encoders.put("scrypt", new SCryptPasswordEncoder());
        encoders.put("sha256", new StandardPasswordEncoder());

        PasswordEncoder passwordEncoder =
                new DelegatingPasswordEncoder(idForEncode, encoders);
        String encode = passwordEncoder.encode("123456");
        System.out.println(encode);
    }

    @Transactional
    @Rollback(value = false)
    @Test
    void postCreate(){
        Category category = new Category();
        category.setName("Java");
        category.setSlug("java");
        categoryDao.insert(category);
        Post post = new Post();
        post.setTitle("test title");
        post.setDescription("test desc");
        post.setContent("test content");
        postDao.insert(post);
        PostCategory postCategory = new PostCategory();
        postCategory.setPostId(post.getId());
        postCategory.setCategoryId(category.getId());
        postCategoryDao.insert(postCategory);
    }

    @Test
    public void testUpload(){
        MinIoUtil.getAllBuckets().forEach(bucket -> System.out.println(bucket.name()));
    }
}
