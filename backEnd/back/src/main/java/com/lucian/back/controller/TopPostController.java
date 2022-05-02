package com.lucian.back.controller;

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

    @PostMapping("top_posts/drag_sort")
    public CommonResult<String> dragSort(@RequestBody List<ChangeTopPostSortParam> changeTopPostSortParam){
        return topPostManager.changeSortNumber(changeTopPostSortParam) ? CommonResult.success(null, "排序成功") : CommonResult.failed("排序失败");
    }
}
