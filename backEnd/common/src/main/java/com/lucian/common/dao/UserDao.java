package com.lucian.common.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucian.common.bean.bo.UserBO;
import com.lucian.common.bean.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author denglingxiang
 * @date 2022/06/15 10:48
 **/
public interface UserDao extends BaseMapper<User> {
    IPage<UserBO> queryListByPage(Page<User> page, @Param(Constants.WRAPPER) Wrapper<User> queryWrapper);
}
