package com.lucian.lucian_blog.bean.translater;

import com.lucian.lucian_blog.bean.entity.Tag;
import com.lucian.lucian_blog.bean.vo.TagIndexVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface Tag2TagIndexVO {

    @Mapping(target = "postCount", ignore = true)
    @Mapping(target = "createdAt", source = "createdAt", dateFormat = "yyyy/MM/dd HH:mm:ss")
    TagIndexVO translate(Tag tag);
}
