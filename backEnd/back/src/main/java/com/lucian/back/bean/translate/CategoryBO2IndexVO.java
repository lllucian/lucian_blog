package com.lucian.back.bean.translate;

import com.lucian.back.bean.bo.CategoryBO;
import com.lucian.back.bean.vo.CategoryIndexVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author lingxiangdeng
 */
@Mapper(componentModel = "spring")
public interface CategoryBO2IndexVO {
    /**
     * 将bo转换为vo
     * @param categoryBO 分类bo
     * @return 分类一览vo
     */
    @Mappings({
            @Mapping(target = "parentName", source = "parentCategory.name"),
            @Mapping(target = "postCount", ignore = true),
            @Mapping(target = "createdAt", dateFormat = "yyyy/MM/dd HH:mm:ss")
    })
    CategoryIndexVO translate(CategoryBO categoryBO);
}
