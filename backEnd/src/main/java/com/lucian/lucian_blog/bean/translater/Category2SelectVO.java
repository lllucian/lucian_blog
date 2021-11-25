package com.lucian.lucian_blog.bean.translater;

import com.lucian.lucian_blog.bean.entity.Category;
import com.lucian.lucian_blog.bean.vo.CategorySelectDataVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface Category2SelectVO {

    @Mapping(source = "id", target = "value")
    @Mapping(source = "name", target ="label")
    CategorySelectDataVO tranCategory2SelectVO(Category category);

    List<CategorySelectDataVO> tranCategory2SelectVOList(List<Category> categories);
}
