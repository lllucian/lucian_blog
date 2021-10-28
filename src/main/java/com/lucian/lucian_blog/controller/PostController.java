package com.lucian.lucian_blog.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lucian.lucian_blog.bean.vo.PostIndexVO;
import com.lucian.lucian_blog.common.CommonResult;
import com.lucian.lucian_blog.form_parm.PostParam;
import com.lucian.lucian_blog.manager.PostManager;
import com.lucian.lucian_blog.query_wrapper.PostQuery;
import com.lucian.lucian_blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("admin")
public class PostController {
    @Autowired
    PostService postService;

    @Autowired
    PostManager postManager;

    @PostMapping("posts")
    public CommonResult<IPage<PostIndexVO>> search(@RequestBody PostQuery postQuery){
        IPage<PostIndexVO> iPage = postManager.queryListByPage(postQuery);
        System.out.println(iPage);
        return CommonResult.successNoMessage(iPage);
    }

    @GetMapping("posts")
    public CommonResult<IPage<PostIndexVO>> index(){
        PostQuery postQuery = new PostQuery();
        IPage<PostIndexVO> iPage = postManager.queryListByPage(postQuery);
        return CommonResult.successNoMessage(iPage);
    }

    @PostMapping("post")
    public CommonResult<String> create(@Valid @RequestBody PostParam postParam){
        return postManager.insert(postParam) ? CommonResult.success(null, "添加成功"): CommonResult.failed("添加失败");
    }
}
