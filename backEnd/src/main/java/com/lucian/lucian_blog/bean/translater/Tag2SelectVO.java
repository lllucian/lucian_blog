package com.lucian.lucian_blog.bean.translater;

import com.lucian.lucian_blog.bean.entity.Tag;
import com.lucian.lucian_blog.bean.vo.TagSelectDataVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface Tag2SelectVO {

    @Mapping(source = "id", target = "value")
    @Mapping(source = "name", target ="label")
    TagSelectDataVO tranTag2SelectVO(Tag tag);

    List<TagSelectDataVO> tranTag2SelectVOList(List<Tag> tags);
}
