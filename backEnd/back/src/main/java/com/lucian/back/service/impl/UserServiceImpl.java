package com.lucian.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucian.back.dao.UserDao;
import com.lucian.back.dao.UserRoleDao;
import com.lucian.back.service.UserService;
import com.lucian.common.bean.bo.UserBO;
import com.lucian.common.bean.entity.Role;
import com.lucian.common.bean.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    UserRoleDao userRoleDao;

    @Override
    public User getUserByNickName(String username) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        return userDao.selectOne(wrapper);
    }

    /**
     * 查找用户名
     * @param nickName the username identifying the user whose data is required.
     * @return 用户
     * @throws UsernameNotFoundException 无法找到用户
     */
    @Override
    public UserDetails loadUserByUsername(String nickName) throws UsernameNotFoundException {
        User user = getUserByNickName(nickName);
        if (user == null) {
            throw new UsernameNotFoundException("用户未找到");
        }
        List<Role> roles = userRoleDao.getRoleListByUserId(user.getId());
        return new UserBO(user, roles);
    }
}
