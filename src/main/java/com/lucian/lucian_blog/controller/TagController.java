package com.lucian.lucian_blog.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lucian.lucian_blog.bean.vo.TagFormVO;
import com.lucian.lucian_blog.bean.vo.TagIndexVO;
import com.lucian.lucian_blog.common.CommonResult;
import com.lucian.lucian_blog.common.ResultCode;
import com.lucian.lucian_blog.form_parm.TagParam;
import com.lucian.lucian_blog.manager.TagManager;
import com.lucian.lucian_blog.query_wrapper.TagQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
public class TagController {

    @Autowired
    TagManager tagManager;

    /**
     * 一览
     * @param tagQuery 查询条件
     * @return 符合条件的对象
     */
    @GetMapping("tags")
    public CommonResult<IPage<TagIndexVO>> index(@RequestBody TagQuery tagQuery){
         return CommonResult.successNoMessage(tagManager.queryListByPage(tagQuery));
    }

    /**
     * 获取指定id的标签
     * @param id 要查询的id
     * @return 指定id的标签
     */
    @GetMapping("tag/{id}")
    public CommonResult<TagFormVO> edit(@PathVariable Integer id){
        TagFormVO formVO = tagManager.findOne(id);
        return formVO == null ? CommonResult.failed(ResultCode.FIND_FAILED) : CommonResult.successNoMessage(formVO);
    }

    /**
     *  创建标签
     * @param tagParam 表单内容
     * @return 是否创建成功
     */
    @PostMapping("tag")
    public CommonResult<String> create(@RequestBody TagParam tagParam){
        return tagManager.createRecord(tagParam) ? CommonResult.success(null, "创建成功！") : CommonResult.failed("创建失败");
    }

    /**
     *  更新标签
     * @param id 标签id
     * @param tagParam 表单内容
     * @return 是否更新成功
     */
    @PutMapping("tag/{id}")
    public CommonResult<String> update(@PathVariable Integer id, @RequestBody TagParam tagParam){
        return tagManager.updateOne(id, tagParam) ? CommonResult.success(null, "更新成功！") : CommonResult.failed("更新失败");
    }

    /**
     * 删除标签
     * @param id 标签id
     * @return 是否删除成功
     */
    @DeleteMapping("tag/{id}")
    public CommonResult<String> delete(@PathVariable Integer id){
        return tagManager.deleteOne(id) ? CommonResult.success(null, "删除成功！") :CommonResult.failed("删除失败");
    }
}
