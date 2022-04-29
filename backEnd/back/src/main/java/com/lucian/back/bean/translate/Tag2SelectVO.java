package com.lucian.back.bean.translate;

import com.lucian.back.bean.vo.TagSelectDataVO;
import com.lucian.common.bean.entity.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author lingxiangdeng
 */
@Mapper(componentModel = "spring")
public interface Tag2SelectVO {

    /**
     * Tag 数据库 转 select形式
     * @param tag 数据库数据
     * @return tag select
     */
    @Mapping(source = "id", target = "value")
    @Mapping(source = "name", target ="label")
    TagSelectDataVO tranTag2SelectVO(Tag tag);

    /**
     * Tag 数据库 转 select形式
     * @param tags 数据库数据
     * @return tags list
     */
    List<TagSelectDataVO> tranTag2SelectVOList(List<Tag> tags);
}
