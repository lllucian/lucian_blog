package com.lucian.lucian_blog.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lucian.lucian_blog.bean.vo.CategoryFormVO;
import com.lucian.lucian_blog.bean.vo.CategoryIndexVO;
import com.lucian.lucian_blog.common.CommonResult;
import com.lucian.lucian_blog.common.ResultCode;
import com.lucian.lucian_blog.form_parm.CategoryParam;
import com.lucian.lucian_blog.manager.CategoryManager;
import com.lucian.lucian_blog.query_wrapper.CategoryQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @PostMapping("category")
    public CommonResult<String> add(@RequestBody @Valid CategoryParam categoryParam){
        return categoryManager.insert(categoryParam) ? CommonResult.success(null, "添加成功"): CommonResult.failed("添加失败");
    }

    @GetMapping("category/{id}")
    public CommonResult<CategoryFormVO> findOne(@PathVariable Integer id){
        CategoryFormVO categoryFormVO = categoryManager.findOne(id);
        return categoryFormVO != null ? CommonResult.successNoMessage(categoryFormVO) : CommonResult.failed(ResultCode.FIND_FAILED);
    }

    @PutMapping("category/{id}")
    public CommonResult<String> update(@PathVariable Integer id, @RequestBody @Valid CategoryParam categoryParam){
        return categoryManager.update(id, categoryParam) ? CommonResult.success(null, "添加成功") : CommonResult.failed("添加失败");
    }

    @DeleteMapping("category/{id}")
    public CommonResult<String> delete(@PathVariable Integer id){
        return categoryManager.delete(id) ? CommonResult.success(null, "删除成功") : CommonResult.failed("删除失败");
    }
}
