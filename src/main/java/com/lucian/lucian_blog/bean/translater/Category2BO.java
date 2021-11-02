package com.lucian.lucian_blog.bean.translater;

import com.lucian.lucian_blog.bean.bo.CategoryBO;
import com.lucian.lucian_blog.bean.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface Category2BO {


    @Mapping(target = "postList", ignore = true)
    @Mapping(target = "parentCategory", ignore = true)
    CategoryBO translate(Category category);
}
