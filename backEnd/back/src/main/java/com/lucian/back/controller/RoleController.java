package com.lucian.back.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lucian.back.bean.vo.RoleFormVO;
import com.lucian.back.bean.vo.RoleIndexVO;
import com.lucian.back.form_parm.RoleParam;
import com.lucian.back.manager.RoleManager;
import com.lucian.back.query_wrapper.RoleQuery;
import com.lucian.common.response.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author denglingxiang
 * @date 2022/06/02 15:05
 **/
@RestController
@RequestMapping("admin")
@Api(tags = "角色")
public class RoleController {

    RoleManager roleManager;

    @Autowired
    public void setRoleManager(RoleManager roleManager) {
        this.roleManager = roleManager;
    }

    /**
     * role一览
     *
     * @param roleQuery 查询条件
     * @return role一览
     */
    @PostMapping("roles")
    @ApiOperation("角色检索")
    public CommonResult<IPage<RoleIndexVO>> search(@RequestBody RoleQuery roleQuery) {
        return CommonResult.successNoMessage(roleManager.list(roleQuery));
    }

    /**
     * role一览 无查询条件
     * @return role一览
     */
    @GetMapping("roles")
    @ApiOperation("角色无条件查询")
    public CommonResult<IPage<RoleIndexVO>> index() {
        return CommonResult.successNoMessage(roleManager.list(null));
    }

    /**
     * 创建角色
     * @param roleParam 角色表单信息
     * @return 是否创建成功
     */
    @PostMapping("role")
    @ApiOperation("创建角色")
    public CommonResult<String> create(@RequestBody @Valid RoleParam roleParam) {
        Boolean insert = roleManager.insert(roleParam);
        return insert ? CommonResult.success(null, "添加成功") : CommonResult.failed("添加失败");
    }

    @GetMapping("role/{id}")
    @ApiOperation("查询角色")
    public CommonResult<RoleFormVO> edit(@PathVariable String id) {
        return CommonResult.successNoMessage(roleManager.roleDetail(id));
    }

    /**
     * 更新角色
     * @param roleParam 角色表单
     * @param id 角色id
     * @return 角色是否更新成功
     */
    @PutMapping("role/{id}")
    @ApiOperation("更新角色")
    public CommonResult<String> update(@RequestBody @Valid RoleParam roleParam, @PathVariable String id) {
        Boolean insert = roleManager.update(id, roleParam);
        return insert ? CommonResult.success(null, "更新成功") : CommonResult.failed("更新失败");
    }

    /**
     * 删除角色
     * @param id  角色id
     * @return 角色是否删除成功
     */
    @DeleteMapping("role/{id}")
    @ApiOperation("删除角色")
    public CommonResult<String> delete(@PathVariable String id) {
        Boolean delete = roleManager.delete(id);
        return delete ? CommonResult.success(null, "删除成功") : CommonResult.failed("删除失败");
    }
}
