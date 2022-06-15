package com.lucian.back.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucian.common.dao.UserRoleDao;
import com.lucian.back.service.UserRoleService;
import com.lucian.common.bean.entity.UserRole;
import org.springframework.stereotype.Service;

/**
 * @author denglingxiang
 * @date 2022/06/13 09:55
 **/
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleDao, UserRole> implements UserRoleService {
}
