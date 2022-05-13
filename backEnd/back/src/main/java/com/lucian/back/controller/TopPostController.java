package com.lucian.back.controller;

import com.lucian.back.bean.vo.PostSelectDataVO;
import com.lucian.back.bean.vo.TopPostIndexVO;
import com.lucian.back.form_parm.ChangeTopPostSortParam;
import com.lucian.back.form_parm.TopPostParam;
import com.lucian.back.manager.TopPostManager;
import com.lucian.common.response.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author lingxiangdeng
 */
@RestController
@RequestMapping("admin")
public class TopPostController{

    TopPostManager topPostManager;

    @Autowired
    public void setTopPostManager(TopPostManager topPostManager) {
        this.topPostManager = topPostManager;
    }

    @GetMapping("top_posts")
    public CommonResult<List<TopPostIndexVO>> index(){
        return CommonResult.successNoMessage(topPostManager.list());
    }

    /**
     *  创建轮播图
     * @param topPostParam 表单内容
     * @return 是否创建成功
     */
    @PostMapping("top_post")
    public CommonResult<String> create(@RequestBody @Valid TopPostParam topPostParam){
        return topPostManager.createRecord(topPostParam) ? CommonResult.success(null, "创建成功！") : CommonResult.failed("创建失败");
    }

    /**
     * 轮播图拖拽排序
     * @param changeTopPostSortParam 轮播图id
     * @return 是否排序成功
     */
    @PostMapping("top_posts/drag_sort")
    public CommonResult<String> dragSort(@RequestBody List<ChangeTopPostSortParam> changeTopPostSortParam){
        return topPostManager.changeSortNumber(changeTopPostSortParam) ? CommonResult.success(null, "排序成功") : CommonResult.failed("排序失败");
    }

    /**
     * 轮播图信息更新
     * @param id 轮播图id
     * @param topPostParam 轮播图信息
     * @return 是否更新成功
     */
    @PostMapping("top_post/{id}")
    public CommonResult<String> update(@PathVariable Integer id, @RequestBody @Valid TopPostParam topPostParam){
        return topPostManager.updateObject(id, topPostParam) ? CommonResult.success(null, "更新成功") : CommonResult.failed("更新失败");
    }

    /**
     * 获取编辑的选中的post信息
     * @param postId 文章id
     * @return select2 文章信息
     */
    @GetMapping("top_post/post_selected/{postId}")
    public CommonResult<PostSelectDataVO> postSelected(@PathVariable Integer postId){
        return CommonResult.successNoMessage(topPostManager.postSelected(postId));
    }

    /**
     * 编辑模式下，select2 检索框 检索要包括现在已选择过的postId
     * @param id 当前topPost id
     * @return select2 集合
     */
    @GetMapping("top_post/post_select/{id}")
    public CommonResult<List<PostSelectDataVO>> editSelect(@PathVariable Integer id, String query){
        return CommonResult.successNoMessage(topPostManager.postList(id, query));
    }
}
