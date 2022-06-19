package com.lucian.common.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lucian.common.bean.bo.UserBO;
import com.lucian.common.bean.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author lingxiangdeng
 */
public interface UserService extends UserDetailsService, IService<User> {
     /**
      * 通过用户名查找用户
      * @param nickName 用户名
      * @return 用户
      */
     User getUserByNickName(String nickName);

     /**
      * 多表查询
      * @param page 分页信息
      * @param queryWrapper 查询条件
      * @return 符合条件的用户集合
      */
     IPage<UserBO> queryListByPage(Page<User> page, Wrapper<User> queryWrapper);
}
