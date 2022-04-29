package com.lucian.back.bean.translate;

import com.lucian.back.bean.vo.CategorySelectDataVO;
import com.lucian.common.bean.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author lingxiangdeng
 */
@Mapper(componentModel = "spring")
public interface Category2SelectVO {

    /**
     * 将单个category转换为只剩 id 和 name的形式
     * @param category 分类
     * @return 分类（只有id和name）
     */
    @Mapping(source = "id", target = "value")
    @Mapping(source = "name", target ="label")
    CategorySelectDataVO tranCategory2SelectVO(Category category);

    /**
     * 将多个category转换为只剩 id 和 name的形式
     * @param categories category 集合
     * @return 分类集合（只有id和name）
     */
    List<CategorySelectDataVO> tranCategory2SelectVOList(List<Category> categories);
}
