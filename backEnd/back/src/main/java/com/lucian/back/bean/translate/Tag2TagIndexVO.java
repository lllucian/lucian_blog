package com.lucian.back.bean.translate;

import com.lucian.back.bean.vo.TagIndexVO;
import com.lucian.common.bean.entity.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author lingxiangdeng
 */
@Mapper(componentModel = "spring")
public interface Tag2TagIndexVO {

    /**
     * 将tags 转换为 index 对象
     * @param tag 标签
     * @return index 对象
     */
    @Mapping(target = "postCount", ignore = true)
    @Mapping(target = "createdAt", source = "createdAt", dateFormat = "yyyy/MM/dd HH:mm:ss")
    TagIndexVO translate(Tag tag);
}
