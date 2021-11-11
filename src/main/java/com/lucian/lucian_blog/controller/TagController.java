package com.lucian.lucian_blog.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucian.lucian_blog.bean.vo.TagIndexVO;
import com.lucian.lucian_blog.common.CommonResult;
import com.lucian.lucian_blog.manager.TagManager;
import com.lucian.lucian_blog.query_wrapper.TagQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
public class TagController {

    @Autowired
    TagManager tagManager;

    @GetMapping("tags")
    public CommonResult<Page<TagIndexVO>> index(@RequestBody TagQuery tagQuery){
         return CommonResult.successNoMessage(tagManager.queryListByPage(tagQuery));
    }
}
