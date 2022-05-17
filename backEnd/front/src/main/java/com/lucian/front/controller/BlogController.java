package com.lucian.front.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lucian.common.response.CommonResult;
import com.lucian.front.bean.vo.BlogDetailVO;
import com.lucian.front.bean.vo.BlogIndexVO;
import com.lucian.front.manager.BlogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * 前端首页
 * @author lingxiangdeng
 */
@RestController
public class BlogController {

    BlogManager blogManager;

    @Autowired
    public void setBlogManager(BlogManager blogManager) {
        this.blogManager = blogManager;
    }

    /**
     * 博客一览
     */
    @GetMapping("blogs/{currentPage}")
    public CommonResult<IPage<BlogIndexVO>> index(@PathVariable Integer currentPage){
        return CommonResult.successNoMessage(blogManager.list(currentPage));
    }

    @GetMapping("blog/{id}")
    public CommonResult<BlogDetailVO> detail(@PathVariable Integer id){
        BlogDetailVO detail = blogManager.detail(id);
        return Objects.nonNull(detail) ? CommonResult.successNoMessage(detail) : CommonResult.failed("没有该文章");
    }
}
