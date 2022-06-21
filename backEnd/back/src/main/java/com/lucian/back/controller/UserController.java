package com.lucian.back.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lucian.back.bean.vo.UserIndexVO;
import com.lucian.back.manager.UserManager;
import com.lucian.back.query_wrapper.UserQuery;
import com.lucian.common.response.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author denglingxiang
 * @date 2022/06/03 13:24
 **/
@RestController
@RequestMapping
public class UserController {

    UserManager userManager;

    @Autowired
    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    /**
     * 无条件get检索
     * @return users
     */
    @GetMapping("users")
    public CommonResult<IPage<UserIndexVO>> index(){
        IPage<UserIndexVO> list = userManager.getList(null);
        return CommonResult.successNoMessage(list);
    }

    /**
     * post 条件检索
     * @param userQuery 检索条件
     * @return users
     */
    @PostMapping("users")
    public CommonResult<IPage<UserIndexVO>> index(UserQuery userQuery){
        IPage<UserIndexVO> list = userManager.getList(userQuery);
        return CommonResult.successNoMessage(list);
    }

    @DeleteMapping("user/{id}")
    public CommonResult<String> remove(@PathVariable String id){
        boolean removeFlag = userManager.removeById(id);
        return removeFlag ? CommonResult.success(null, "删除成功") : CommonResult.failed("删除失败");
    }
}
