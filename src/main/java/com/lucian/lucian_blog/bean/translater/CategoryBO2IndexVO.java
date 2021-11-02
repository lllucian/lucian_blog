package com.lucian.lucian_blog.bean.translater;

import com.lucian.lucian_blog.bean.bo.CategoryBO;
import com.lucian.lucian_blog.bean.vo.CategoryIndexVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CategoryBO2IndexVO {

    @Mappings({
            @Mapping(target = "parentName", source = "parentCategory.name"),
            @Mapping(target = "postCount", ignore = true),
            @Mapping(target = "createdAt", dateFormat = "yyyy/MM/dd HH:mm:ss")
    })
    CategoryIndexVO translate(CategoryBO categoryBO);
}
