package com.lucian.back.manager;

import com.lucian.back.bean.translate.User2SelectVO;
import com.lucian.back.bean.vo.UserSelectDataVO;
import com.lucian.back.form_parm.UserParam;
import com.lucian.back.query_wrapper.UserSelectQuery;
import com.lucian.back.service.UserService;
import com.lucian.common.bean.entity.User;
import com.lucian.common.bean.enums.EnabledStatusEnum;
import com.lucian.common.bean.enums.LockedStatusEnum;
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

    /**
     * 获取user下拉列表种的数据
     * @param userSelectQuery 查询条件
     * @return 符合查询条件的数据
     */
    public List<UserSelectDataVO> selectData(UserSelectQuery userSelectQuery){
        if (userSelectQuery == null) {
            userSelectQuery = new UserSelectQuery();
        }
        List<User> list = userService.list(userSelectQuery.getQueryWrapper());
        return user2SelectVO.translateList(list);
    }

    @Transactional
    public boolean createUser(UserParam userParam) {
        User user = new User();
        BeanUtils.copyProperties(userParam, user);
        userService.save(user);
        if (user.getEnabled() == null){
            user.setEnabled(EnabledStatusEnum.ENABLED);
        }
        if (user.getLocked() == null) {
            user.setLocked(LockedStatusEnum.UNLOCKED);
        }
        return false;
    }
}
