package com.lucian.lucian_blog.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lucian.lucian_blog.bean.vo.CategoryIndexVO;
import com.lucian.lucian_blog.common.CommonResult;
import com.lucian.lucian_blog.manager.CategoryManager;
import com.lucian.lucian_blog.query_wrapper.CategoryQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
public class CategoryController {

    @Autowired
    CategoryManager categoryManager;

    @GetMapping("categories")
    public CommonResult<IPage<CategoryIndexVO>> index(){
        CategoryQuery categoryQuery = new CategoryQuery();
        return CommonResult.successNoMessage(categoryManager.queryList(categoryQuery));
    }

    @PostMapping("categories")
    public CommonResult<IPage<CategoryIndexVO>> search(@RequestBody CategoryQuery categoryQuery){
        return CommonResult.successNoMessage(categoryManager.queryList(categoryQuery));
    }
}
