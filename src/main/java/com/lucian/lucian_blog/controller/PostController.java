package com.lucian.lucian_blog.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucian.lucian_blog.dto.post.PostDTO;
import com.lucian.lucian_blog.entity.Post;
import com.lucian.lucian_blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {
    @Autowired
    PostService postService;

    @GetMapping("/posts")
    public Page<PostDTO> index(){
        Page<PostDTO> list = postService.list(new Page<>(0, 10));
        return list;
    }

    @GetMapping("/post")
    public String newPost(){
        return "new";
    }
}
