package com.lucian.lucian_blog.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lucian.lucian_blog.annotation.Log;
import com.lucian.lucian_blog.bean.vo.SysLogIndexVO;
import com.lucian.lucian_blog.common.CommonResult;
import com.lucian.lucian_blog.manager.SysLogManager;
import com.lucian.lucian_blog.query_wrapper.SysLogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
public class SysLogController {

    SysLogManager sysLogManager;

    @Autowired
    public void setSysLogManager(SysLogManager sysLogManager) {
        this.sysLogManager = sysLogManager;
    }

    @Log(title = "查看日志")
    @PostMapping("sys_logs")
    public CommonResult<IPage<SysLogIndexVO>> index(@RequestBody SysLogQuery sysLogQuery){
        return CommonResult.successNoMessage(sysLogManager.queryListByPage(sysLogQuery));
    }
}
