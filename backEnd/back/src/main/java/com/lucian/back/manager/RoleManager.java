package com.lucian.back.manager;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucian.back.bean.translate.Role2IndexVO;
import com.lucian.back.bean.vo.RoleFormVO;
import com.lucian.back.bean.vo.RoleIndexVO;
import com.lucian.back.form_parm.RoleParam;
import com.lucian.back.query_wrapper.RoleQuery;
import com.lucian.back.service.RoleService;
import com.lucian.common.bean.entity.Role;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author denglingxiang
 * @date 2022/06/01 13:57
 **/
@Component
public class RoleManager {
    RoleService roleService;

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    Role2IndexVO role2IndexVO;

    @Autowired
    public void setRole2IndexVO(Role2IndexVO role2IndexVO) {
        this.role2IndexVO = role2IndexVO;
    }

    /**
     * 角色数据一览
     * @param roleQuery 查询条件
     * @return 角色一览
     */
    public IPage<RoleIndexVO> list(RoleQuery roleQuery) {
        if (roleQuery == null){
            roleQuery = new RoleQuery();
        }
        Page<Role> page = new Page<>(roleQuery.getCurrent(), roleQuery.getSize());
        IPage<Role> pageBOPage = roleService.page(page, roleQuery.getQueryWrapper());
        IPage<RoleIndexVO> roleIndexVOIPage = new Page<>();
        BeanUtils.copyProperties(pageBOPage, roleIndexVOIPage);
        return roleIndexVOIPage.setRecords(role2IndexVO.translate(pageBOPage.getRecords()));
    }

    /**
     * 创建角色数据
     * @param roleParam 角色form
     * @return 是否创建成功
     */
    public Boolean insert (RoleParam roleParam){
        Role role = new Role();
        BeanUtils.copyProperties(roleParam, role);
        role.setName("ROLE_" + roleParam.getName());
        return roleService.save(role);
    }

    /**
     * 角色详细
     * @param id 角色id
     * @return 角色详情
     */
    public RoleFormVO roleDetail(String  id){
        Role role = roleService.getById(id);
        if (Objects.isNull(role)){
            return null;
        }
        RoleFormVO roleFormVO = new RoleFormVO();
        BeanUtils.copyProperties(role, roleFormVO);
        return roleFormVO;
    }

    /**
     * 更新角色
     * @param id 角色id
     * @param roleParam 角色参数
     * @return 是否更新成功
     */
    public Boolean update (String id, RoleParam roleParam) {
        Role role = roleService.getById(id);
        if (Objects.isNull(role)){
            return false;
        }
        BeanUtils.copyProperties(roleParam, role);
        role.setName("ROLE_" + roleParam.getName());
        return roleService.updateById(role);
    }

    /**
     * 删除角色
     * @param id 角色id
     * @return 是否删除成功
     */
    public Boolean delete(String id) {
        Role role = roleService.getById(id);
        if (Objects.isNull(role)){
            return false;
        }
        return roleService.removeById(id);
    }
}
