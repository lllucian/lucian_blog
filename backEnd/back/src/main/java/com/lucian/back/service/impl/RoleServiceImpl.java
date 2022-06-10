package com.lucian.back.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucian.back.dao.RoleDao;
import com.lucian.back.service.RoleService;
import com.lucian.common.bean.entity.Role;
import org.springframework.stereotype.Service;

/**
 * @author denglingxiang
 * @date 2022/06/01 13:53
 **/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements RoleService {
}
