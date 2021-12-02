package com.lucian.lucian_blog.controller;

import com.lucian.lucian_blog.bean.vo.CategorySelectDataVO;
import com.lucian.lucian_blog.bean.vo.TagSelectDataVO;
import com.lucian.lucian_blog.common.CommonResult;
import com.lucian.lucian_blog.manager.CategoryManager;
import com.lucian.lucian_blog.manager.TagManager;
import com.lucian.lucian_blog.query_wrapper.CategorySelectQuery;
import com.lucian.lucian_blog.query_wrapper.TagSelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin/fetch")
public class FetchController {
    @Autowired
    CategoryManager categoryManager;

    @Autowired
    TagManager tagManager;

    /**
     * select检索category
     * @param categorySelectQuery 检索条件
     * @return 检索后的数据
     */
    @PostMapping("categories")
    public CommonResult<List<CategorySelectDataVO>> searchCategory(@RequestBody(required = false) CategorySelectQuery categorySelectQuery){
        List<CategorySelectDataVO> categorySelectDataVO = categoryManager.selectSearch(categorySelectQuery);
        return CommonResult.successNoMessage(categorySelectDataVO);
    }

    /**
     * select检索tag
     * @param tagSelectQuery 检索条件
     * @return 检索后的数据
     */
    @PostMapping("tags")
    public CommonResult<List<TagSelectDataVO>> searchTag(@RequestBody(required = false) TagSelectQuery tagSelectQuery){
        List<TagSelectDataVO> tagSelectDataVO = tagManager.selectSearch(tagSelectQuery);
        return CommonResult.successNoMessage(tagSelectDataVO);
    }

    /**
     * 获取可以选择的分类
     * @param categoryId 当前分类id
     * @return 获取可以选择的分类
     */
    @PostMapping({"getParentId", "getParentId/{categoryId}"})
    public CommonResult<List<CategorySelectDataVO>> getCanSelectCategory(@PathVariable(required = false) Integer categoryId){
        return CommonResult.successNoMessage(categoryManager.parentCategory(categoryId));
    }
}
