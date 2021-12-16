package com.lucian.lucian_blog.bean.translater;

import com.lucian.lucian_blog.bean.entity.User;
import com.lucian.lucian_blog.bean.vo.UserSelectDataVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface User2SelectVO {
    @Mapping(source = "id", target = "value")
    @Mapping(source = "username", target = "label")
    UserSelectDataVO translate(User user);

    List<UserSelectDataVO> translateList(List<User> users);
}
