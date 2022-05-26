package com.lucian.back.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lucian.back.bean.vo.PostFormVO;
import com.lucian.back.bean.vo.PostIndexVO;
import com.lucian.back.form_parm.PostParam;
import com.lucian.back.manager.PostManager;
import com.lucian.back.query_wrapper.PostQuery;
import com.lucian.back.service.PostService;
import com.lucian.common.annotation.Log;
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
public class PostController {
    @Autowired
    PostService postService;

    @Autowired
    PostManager postManager;

    /**
     * 检索
     * @param postQuery 检索条件数据
     * @return 检索后的数据
     */
    @Log(title = "检索文章")
    @PostMapping("posts")
    public CommonResult<IPage<PostIndexVO>> search(@RequestBody PostQuery postQuery){
        IPage<PostIndexVO> iPage = postManager.queryListByPage(postQuery);
        return CommonResult.successNoMessage(iPage);
    }

    /**
     * 一览
     * @return 一览数据
     */
    @Log(title = "文章一览")
    @GetMapping("posts")
    public CommonResult<IPage<PostIndexVO>> index(){
        IPage<PostIndexVO> iPage = postManager.queryListByPage(null);
        return CommonResult.successNoMessage(iPage);
    }

    /**
     * 创建
     * @param postParam post表单数据
     * @return 是否添加成功
     */
    @PostMapping("post")
    public CommonResult<String> create(@Valid @RequestBody PostParam postParam){
        return postManager.insert(postParam) ? CommonResult.success(null, "添加成功"): CommonResult.failed("添加失败");
    }

    /**
     * 编辑
     * @param id blog id
     * @return 博客详情数据
     */
    @GetMapping("post/{id}")
    public CommonResult<PostFormVO> edit(@PathVariable String id){
        PostFormVO postFormVO = postManager.postDetail(id);
        if (postFormVO == null) {
            return CommonResult.failed(ResultCode.FIND_FAILED);
        }
        return CommonResult.successNoMessage(postFormVO);
    }

    /**
     * 更新
     * @param id blog id
     * @param postParam 表单数据
     * @return 是否更新成功
     */
    @PutMapping("post/{id}")
    public CommonResult<String> update(@PathVariable String id, @Valid @RequestBody PostParam postParam){
        return postManager.update(id, postParam) ? CommonResult.success(null, "修改成功"): CommonResult.failed("修改失败");
    }

    /**
     * 删除文章
     * @param id blog id
     * @return 是否删除成功
     */
    @DeleteMapping("post/{id}")
    public CommonResult<String> delete(@PathVariable String id) {
        return postManager.deletePost(id) ? CommonResult.success(null, "删除成功") : CommonResult.failed("删除失败");
    }
}
