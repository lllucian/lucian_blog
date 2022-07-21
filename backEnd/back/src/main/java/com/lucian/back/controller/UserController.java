package com.lucian.back.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lucian.back.bean.vo.UserFormVO;
import com.lucian.back.bean.vo.UserIndexVO;
import com.lucian.back.form_parm.UserEditParam;
import com.lucian.back.form_parm.UserParam;
import com.lucian.back.manager.UserManager;
import com.lucian.back.query_wrapper.UserQuery;
import com.lucian.common.response.CommonResult;
import com.lucian.common.response.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    /**
     * 删除用户
     * @param id 用户id
     * @return 用户是否删除成功
     */
    @DeleteMapping("user/{id}")
    public CommonResult<String> remove(@PathVariable String id){
        boolean removeFlag = userManager.removeById(id);
        return removeFlag ? CommonResult.success(null, "删除成功") : CommonResult.failed("删除失败");
    }

    /**
     * 添加用户
     * @param userParam 用户参数
     * @return 是否添加成功
     */
    @PostMapping("user")
    public CommonResult<String> addUser(@Valid @RequestBody UserParam userParam) {
        boolean success = userManager.createUser(userParam);
        return success ? CommonResult.success(null, "添加成功") : CommonResult.failed("添加失败");
    }

    @GetMapping("user/{id}")
    public CommonResult<UserFormVO> edit(@PathVariable String id){
        UserFormVO userFormVO = userManager.userDetails(id);
        if (userFormVO == null) {
            return CommonResult.failed(ResultCode.FIND_FAILED);
        }
        return CommonResult.successNoMessage(userFormVO);
    }

    @PutMapping("user")
    public CommonResult<String> update(@RequestBody @Valid UserEditParam userEditParam){
        return userManager.updateUser(userEditParam) ? CommonResult.success(null, "更新成功") : CommonResult.failed("更新失败");
    }
}
