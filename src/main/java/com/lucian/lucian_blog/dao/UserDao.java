package com.lucian.lucian_blog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lucian.lucian_blog.bean.bo.UserBO;
import com.lucian.lucian_blog.bean.entity.User;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface UserDao extends BaseMapper<User> {
    @Select("select * from users where nick_name = #{userName} and deleted_at is null")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "id", property = "roles",
            many = @Many(
                    select = "com.lucian.lucian_blog.dao.RoleDao.queryListByUserId"
            ))
    })
    UserBO loadUserByUsername(String userName);
}
