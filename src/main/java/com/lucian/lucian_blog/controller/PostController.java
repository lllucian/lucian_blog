package com.lucian.lucian_blog.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lucian.lucian_blog.bean.vo.PostIndexVO;
import com.lucian.lucian_blog.query_wrapper.PostQuery;
import com.lucian.lucian_blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {
    @Autowired
    PostService postService;

    @PostMapping("/admin/posts")
    public IPage<PostIndexVO> search(@RequestBody PostQuery postQuery){
        IPage<PostIndexVO> iPage = postService.queryListByPage(postQuery);
        System.out.println(iPage);
        return iPage;
    }

    @GetMapping("/admin/posts")
    public IPage<PostIndexVO> index(){
        PostQuery postQuery = new PostQuery();
        IPage<PostIndexVO> iPage = postService.queryListByPage(postQuery);
        return iPage;
    }
}
