package com.lucian.back.controller;

import com.lucian.back.bean.vo.CategorySelectDataVO;
import com.lucian.back.bean.vo.TagSelectDataVO;
import com.lucian.back.bean.vo.UserSelectDataVO;
import com.lucian.back.manager.CategoryManager;
import com.lucian.back.manager.TagManager;
import com.lucian.back.manager.UserManager;
import com.lucian.back.query_wrapper.CategorySelectQuery;
import com.lucian.back.query_wrapper.PostQuery;
import com.lucian.back.query_wrapper.TagSelectQuery;
import com.lucian.back.query_wrapper.UserSelectQuery;
import com.lucian.common.response.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lingxiangdeng
 */
@RestController
@RequestMapping("admin/fetch")
public class FetchController {
    CategoryManager categoryManager;

    @Autowired
    public void setCategoryManager(CategoryManager categoryManager) {
        this.categoryManager = categoryManager;
    }

    TagManager tagManager;
    @Autowired
    public void setTagManager(TagManager tagManager) {
        this.tagManager = tagManager;
    }

    UserManager userManager;

    @Autowired
    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

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

    @PostMapping("categories/selected")
    public CommonResult<List<CategorySelectDataVO>> getOneSelectCategory(@RequestBody PostQuery postQuery){
        return CommonResult.successNoMessage(categoryManager.getSelectData(postQuery.getCategories()));
    }

    @PostMapping("tags/selected")
    public CommonResult<List<TagSelectDataVO>> getOneSelectTag(@RequestBody PostQuery postQuery){
        return CommonResult.successNoMessage(tagManager.getSelectData(postQuery.getTags()));
    }

    /**
     * user下拉列表数据获取
     * @param userSelectQuery 查询条件
     * @return 符合查询条件的数据
     */
    @PostMapping("users")
    public CommonResult<List<UserSelectDataVO>> searchUser(@RequestBody UserSelectQuery userSelectQuery){
        return CommonResult.successNoMessage(userManager.selectData(userSelectQuery));
    }
}
