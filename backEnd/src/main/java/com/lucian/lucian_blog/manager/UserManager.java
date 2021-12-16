package com.lucian.lucian_blog.manager;

import com.lucian.lucian_blog.bean.entity.User;
import com.lucian.lucian_blog.bean.translater.User2SelectVO;
import com.lucian.lucian_blog.bean.vo.UserSelectDataVO;
import com.lucian.lucian_blog.query_wrapper.UserSelectQuery;
import com.lucian.lucian_blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
        if (userSelectQuery == null) userSelectQuery = new UserSelectQuery();
        List<User> list = userService.list(userSelectQuery.getQueryWrapper());
        return user2SelectVO.translateList(list);
    }
}
