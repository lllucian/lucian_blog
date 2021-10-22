package com.lucian.lucian_blog.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

    @RequestMapping("/admin/posts")
    public String index(){
        return "hello admin";
    }
}
