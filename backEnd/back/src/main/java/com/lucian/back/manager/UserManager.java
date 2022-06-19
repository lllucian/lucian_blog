package com.lucian.back.manager;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucian.back.bean.translate.User2SelectVO;
import com.lucian.back.bean.translate.UserBO2IndexVO;
import com.lucian.back.bean.vo.UserIndexVO;
import com.lucian.back.bean.vo.UserSelectDataVO;
import com.lucian.back.form_parm.UserParam;
import com.lucian.back.query_wrapper.UserQuery;
import com.lucian.back.query_wrapper.UserSelectQuery;
import com.lucian.back.service.RoleService;
import com.lucian.back.service.UserRoleService;
import com.lucian.common.bean.bo.UserBO;
import com.lucian.common.bean.entity.Role;
import com.lucian.common.bean.entity.User;
import com.lucian.common.bean.entity.UserRole;
import com.lucian.common.service.UserService;
import com.lucian.common.utils.IpUtils;
import com.lucian.common.utils.ServletUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author lingxiangdeng
 */
@Component
public class UserManager {
    UserService userService;

    @Autowired
    public void setUserDao(UserService userService) {
        this.userService = userService;
    }

    User2SelectVO user2SelectVO;

    @Autowired
    public void setUser2SelectVO(User2SelectVO user2SelectVO) {
        this.user2SelectVO = user2SelectVO;
    }

    RoleService roleService;

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    UserRoleService userRoleService;

    @Autowired
    public void setUserRoleService(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    /**
     * 获取user下拉列表种的数据
     *
     * @param userSelectQuery 查询条件
     * @return 符合查询条件的数据
     */
    public List<UserSelectDataVO> selectData(UserSelectQuery userSelectQuery) {
        if (userSelectQuery == null) {
            userSelectQuery = new UserSelectQuery();
        }
        List<User> list = userService.list(userSelectQuery.getQueryWrapper());
        return user2SelectVO.translateList(list);
    }

    /**
     * 创建用户
     *
     * @param userParam 用户信息
     * @return 是否创建成功
     */
    @Transactional
    public boolean createUser(UserParam userParam) {
        User user = new User();
        BeanUtils.copyProperties(userParam, user);
        boolean save = userService.save(user);
        if (!save) return false;
        if (userParam.getRoleIds() == null || userParam.getRoleIds().size() == 0) return true;
        // 赋权
        List<Role> roles = roleService.listByIds(userParam.getRoleIds());
        if (roles != null && roles.size() != 0) {
            roles.forEach(role -> {
                UserRole userRole = new UserRole();
                userRole.setUserId(user.getId());
                userRole.setRoleId(role.getId());
                userRoleService.save(userRole);
            });
        }
        return true;
    }

    /**
     * 记录登陆的ip
     * @param username 用户名
     */
    public void recordUserInfo(String username){
        User user = userService.getUserByNickName(username);
        if (Strings.isNotBlank(user.getSignCurrentIp())) {
            user.setSignLastIp(user.getSignCurrentIp());
        }
        user.setSignCurrentIp(IpUtils.getIpAddr(ServletUtils.getRequest()));
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", user.getId());
        wrapper.set("sign_current_ip", user.getSignCurrentIp());
        wrapper.set("sign_last_ip", user.getSignLastIp());
        userService.update(wrapper);
    }

    /**
     * 获取符合条件的list
     * @param userQuery 查询条件
     * @return
     */
    public IPage<UserIndexVO> getList(UserQuery userQuery){
        if (userQuery == null) {
            userQuery = new UserQuery();
        }
        Page<User> page = new Page<>(userQuery.getCurrent(), userQuery.getSize());
        IPage<UserBO> userBOIPage = userService.queryListByPage(page, userQuery.getQueryWrapper());
        UserBO2IndexVO UserBO2IndexVO = SpringUtil.getBean(UserBO2IndexVO.class);
        List<UserBO> records = userBOIPage.getRecords();
        if (CollUtil.isEmpty(records)) {
            return null;
        }
        List<UserIndexVO> userIndexVOS = UserBO2IndexVO.translate(records);
        IPage<UserIndexVO> userIndexVOIPage = new Page<>();
        BeanUtils.copyProperties(userBOIPage, userIndexVOIPage);
        userIndexVOIPage.setRecords(userIndexVOS);
        return userIndexVOIPage;
    }
}
