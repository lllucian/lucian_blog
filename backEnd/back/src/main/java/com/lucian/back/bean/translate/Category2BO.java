package com.lucian.back.bean.translate;

import com.lucian.back.bean.bo.CategoryBO;
import com.lucian.common.bean.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author lingxiangdeng
 */
@Mapper(componentModel = "spring")
public interface Category2BO {

    /**
     * 将单个category糅合成父子类形式
     * @param category 分类
     * @return 父子分类
     */
    @Mapping(target = "postList", ignore = true)
    @Mapping(target = "parentCategory", ignore = true)
    CategoryBO translate(Category category);
}
