package com.lucian.back.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lucian.back.bean.vo.SysLogIndexVO;
import com.lucian.back.manager.SysLogManager;
import com.lucian.back.query_wrapper.SysLogQuery;
import com.lucian.common.annotation.Log;
import com.lucian.common.response.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lingxiangdeng
 */
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
