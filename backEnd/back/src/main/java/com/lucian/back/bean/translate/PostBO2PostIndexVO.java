package com.lucian.back.bean.translate;

import com.lucian.back.bean.bo.PostBO;
import com.lucian.back.bean.vo.PostIndexVO;
import com.lucian.common.bean.entity.Category;
import com.lucian.common.bean.entity.Tag;
import org.mapstruct.*;

import java.util.List;
import java.util.stream.Collectors;


/**
 * @author lingxiangdeng
 */
@Mapper(componentModel = "spring")
public abstract class PostBO2PostIndexVO {
    /**
     * 将post数据库对象 转换为 一览对象
     * @param postBOs 数据库对象
     * @return 一览对象
     */
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "post.title", target = "title"),
            @Mapping(source = "post.description", target = "description"),
            @Mapping(source = "post.createdAt", target = "createdAt", dateFormat = "yyyy/MM/dd HH:mm:ss"),
            @Mapping(target = "categories", ignore = true),
            @Mapping(target = "tags", ignore = true),
            @Mapping(target = "updatedAt", source = "post.updatedAt", dateFormat = "yyyy/MM/dd HH:mm:ss")
    })
    public abstract PostIndexVO convertToPostIndexVo(PostBO postBOs);

    /**
     * 分类list 标签list 无法正常使用mapStruct进行转换
     * @param postBO post数据库对象
     * @param postIndexVO 一览对象
     */
    @AfterMapping
    protected void setCategories(PostBO postBO, @MappingTarget PostIndexVO postIndexVO){
        List<Category> categories = postBO.getCategories();
        List<String> caCollect = categories.stream().map(Category::getName).collect(Collectors.toList());
        postIndexVO.setCategories(caCollect);

        List<Tag> tags = postBO.getTags();
        List<String> tagCollect = tags.stream().map(Tag::getName).collect(Collectors.toList());
        postIndexVO.setTags(tagCollect);
    }
}
