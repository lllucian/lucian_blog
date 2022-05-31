package com.lucian.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
public class UserServiceImpl implements UserService {

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

    @Override
    // 查找用户名
    public UserDetails loadUserByUsername(String nickName) throws UsernameNotFoundException {
        User user = getUserByNickName(nickName);
        if (user == null) {
            throw new UsernameNotFoundException("用户未找到");
        }
        List<Role> roles = userRoleDao.getRoleListByUserId(user.getId());
        return new UserBO(user, roles);
    }


    @Override
    public boolean saveBatch(Collection<User> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<User> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<User> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(User entity) {
        return false;
    }

    @Override
    public User getOne(Wrapper<User> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper<User> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<User> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public BaseMapper<User> getBaseMapper() {
        return userDao;
    }

    @Override
    public Class<User> getEntityClass() {
        return User.class;
    }
}
