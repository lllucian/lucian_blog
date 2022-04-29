package com.lucian.back.bean.translate;

import com.lucian.back.bean.vo.UserSelectDataVO;
import com.lucian.common.bean.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author lingxiangdeng
 */
@Mapper(componentModel = "spring")
public interface User2SelectVO {
    /**
     * 将user转换为只有id 和 username的user对象
     * @param user 用户
     * @return 只有id 和 username的user对象
     */
    @Mapping(source = "id", target = "value")
    @Mapping(source = "username", target = "label")
    UserSelectDataVO translate(User user);

    /**
     * 将user转换为只有id 和 username的user对象
     * @param users 用户 select
     * @return 只有id 和 username的user对象s
     */
    List<UserSelectDataVO> translateList(List<User> users);
}
