package com.lucian.lucian_blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lucian.lucian_blog.bean.bo.UserBO;
import com.lucian.lucian_blog.bean.entity.Role;
import com.lucian.lucian_blog.bean.entity.User;
import com.lucian.lucian_blog.dao.UserDao;
import com.lucian.lucian_blog.dao.UserRoleDao;
import com.lucian.lucian_blog.service.UserService;
import org.springframework.beans.BeanUtils;
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
    public User getUserByNickName(String nickName) {
        QueryWrapper<com.lucian.lucian_blog.bean.entity.User> wrapper = new QueryWrapper<>();
        wrapper.eq("nick_name", nickName);
        return userDao.selectOne(wrapper);
    }

    // 查找用户名
    @Override
    public UserDetails loadUserByUsername(String nickName) throws UsernameNotFoundException {
        User user = getUserByNickName(nickName);
        if (user == null) throw new UsernameNotFoundException("用户未找到");
        List<Role> roles = userRoleDao.getRoleListByUserId(user.getId());
        return new UserBO(user, roles);
    }


    /**
     * 插入（批量）
     *
     * @param entityList 实体对象集合
     * @param batchSize  插入批次数量
     */
    @Override
    public boolean saveBatch(Collection<UserBO> entityList, int batchSize) {
        return false;
    }

    /**
     * 批量修改插入
     *
     * @param entityList 实体对象集合
     * @param batchSize  每次的数量
     */
    @Override
    public boolean saveOrUpdateBatch(Collection<UserBO> entityList, int batchSize) {
        return false;
    }

    /**
     * 根据ID 批量更新
     *
     * @param entityList 实体对象集合
     * @param batchSize  更新批次数量
     */
    @Override
    public boolean updateBatchById(Collection<UserBO> entityList, int batchSize) {
        return false;
    }

    /**
     * TableId 注解存在更新记录，否插入一条记录
     *
     * @param entity 实体对象
     */
    @Override
    public boolean saveOrUpdate(UserBO entity) {
        return false;
    }

    /**
     * 根据 Wrapper，查询一条记录
     *
     * @param queryWrapper 实体对象封装操作类 {@link QueryWrapper}
     * @param throwEx      有多个 result 是否抛出异常
     */
    @Override
    public UserBO getOne(Wrapper<UserBO> queryWrapper, boolean throwEx) {
        return null;
    }

    /**
     * 根据 Wrapper，查询一条记录
     *
     * @param queryWrapper 实体对象封装操作类 {@link QueryWrapper}
     */
    @Override
    public Map<String, Object> getMap(Wrapper<UserBO> queryWrapper) {
        return null;
    }

    /**
     * 根据 Wrapper，查询一条记录
     *
     * @param queryWrapper 实体对象封装操作类 {@link QueryWrapper}
     * @param mapper       转换函数
     */
    @Override
    public <V> V getObj(Wrapper<UserBO> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    /**
     * 获取对应 entity 的 BaseMapper
     *
     * @return BaseMapper
     */
    @Override
    public BaseMapper<UserBO> getBaseMapper() {
        return null;
    }

    /**
     * 获取 entity 的 class
     *
     * @return {@link Class<User>}
     */
    @Override
    public Class<UserBO> getEntityClass() {
        return null;
    }
}
