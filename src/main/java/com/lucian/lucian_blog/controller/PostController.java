package com.lucian.lucian_blog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

    @GetMapping("/posts")
    public String index(){
        return "hello post!!";
    }

    @GetMapping("/admin/posts")
    public String hello(){
        return "hello admin!!";
    }
}
