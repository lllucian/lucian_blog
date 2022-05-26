package com.lucian.back.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lucian.back.bean.vo.TagFormVO;
import com.lucian.back.bean.vo.TagIndexVO;
import com.lucian.back.form_parm.TagParam;
import com.lucian.back.manager.TagManager;
import com.lucian.back.query_wrapper.TagQuery;
import com.lucian.common.response.CommonResult;
import com.lucian.common.response.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author lingxiangdeng
 */
@RestController
@RequestMapping("admin")
public class TagController {

    TagManager tagManager;

    @Autowired
    public void setTagManager(TagManager tagManager){
        this.tagManager = tagManager;
    }

    /**
     * 无条件查询
     * @return 标签集合
     */
    @GetMapping("tags")
    public CommonResult<IPage<TagIndexVO>> index(){
        TagQuery tagQuery = new TagQuery();
        return CommonResult.successNoMessage(tagManager.queryListByPage(tagQuery));
    }

    /**
     * 一览
     * @param tagQuery 查询条件
     * @return 符合条件的对象
     */
    @PostMapping("tags")
    public CommonResult<IPage<TagIndexVO>> search(@RequestBody TagQuery tagQuery){
         return CommonResult.successNoMessage(tagManager.queryListByPage(tagQuery));
    }

    /**
     * 获取指定id的标签
     * @param id 要查询的id
     * @return 指定id的标签
     */
    @GetMapping("tag/{id}")
    public CommonResult<TagFormVO> edit(@PathVariable String id){
        TagFormVO formVO = tagManager.findOne(id);
        return formVO == null ? CommonResult.failed(ResultCode.FIND_FAILED) : CommonResult.successNoMessage(formVO);
    }

    /**
     *  创建标签
     * @param tagParam 表单内容
     * @return 是否创建成功
     */
    @PostMapping("tag")
    public CommonResult<String> create(@RequestBody @Valid TagParam tagParam){
        return tagManager.createRecord(tagParam) ? CommonResult.success(null, "创建成功！") : CommonResult.failed("创建失败");
    }

    /**
     *  更新标签
     * @param id 标签id
     * @param tagParam 表单内容
     * @return 是否更新成功
     */
    @PutMapping("tag/{id}")
    public CommonResult<String> update(@PathVariable String id, @RequestBody @Valid TagParam tagParam){
        return tagManager.updateOne(id, tagParam) ? CommonResult.success(null, "更新成功！") : CommonResult.failed("更新失败");
    }

    /**
     * 删除标签
     * @param id 标签id
     * @return 是否删除成功
     */
    @DeleteMapping("tag/{id}")
    public CommonResult<String> delete(@PathVariable String id){
        return tagManager.deleteOne(id) ? CommonResult.success(null, "删除成功！") :CommonResult.failed("删除失败");
    }
}
