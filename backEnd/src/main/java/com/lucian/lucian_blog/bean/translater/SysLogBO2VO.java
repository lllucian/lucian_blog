package com.lucian.lucian_blog.bean.translater;

import com.lucian.lucian_blog.bean.bo.SysLogBO;
import com.lucian.lucian_blog.bean.vo.SysLogIndexVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SysLogBO2VO {
    @Mapping(source = "user.username", target = "username")
    SysLogIndexVO translate(SysLogBO sysLogBO);
}
