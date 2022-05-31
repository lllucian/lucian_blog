package com.lucian.front.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lucian.common.bean.es_entity.EsPostEntity;
import com.lucian.common.response.CommonResult;
import com.lucian.front.bean.vo.BlogDetailVO;
import com.lucian.front.bean.vo.BlogIndexVO;
import com.lucian.front.es_service.ESPostService;
import com.lucian.front.manager.BlogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    ESPostService esPostService;

    @Autowired
    public void setEsPostService(ESPostService esPostService) {
        this.esPostService = esPostService;
    }

    /**
     * 博客一览
     */
    @GetMapping("blogs/{currentPage}")
    public CommonResult<IPage<BlogIndexVO>> index(@PathVariable Integer currentPage){
        return CommonResult.successNoMessage(blogManager.list(currentPage));
    }

    @GetMapping("blog/{id}")
    public CommonResult<BlogDetailVO> detail(@PathVariable String id){
        BlogDetailVO detail = blogManager.detail(id);
        return CommonResult.successNoMessage(detail);
    }

    /**
     * es检索文章信息
     * @param keyword 关键词
     * @return 检索到的文章
     */
    @GetMapping("blog/search/{keyword}")
    public CommonResult<List<SearchHit<EsPostEntity>>> search(@PathVariable String keyword){
        List<SearchHit<EsPostEntity>> searchHits = esPostService.keywordSearch(keyword);
        return CommonResult.successNoMessage(searchHits);
    }
}
