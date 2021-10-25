package com.lucian.lucian_blog.bean.translater;

import com.lucian.lucian_blog.bean.bo.PostBO;
import com.lucian.lucian_blog.bean.entity.Category;
import com.lucian.lucian_blog.bean.entity.Tag;
import com.lucian.lucian_blog.bean.vo.PostIndexVO;
import javafx.geometry.Pos;
import org.mapstruct.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public abstract class PostBO2PostIndexVO {
    @Mappings({
            @Mapping(source = "post.id", target = "id"),
            @Mapping(source = "post.title", target = "title"),
            @Mapping(source = "post.description", target = "description"),
            @Mapping(source = "post.createdAt", target = "createdAt", dateFormat = "yyyy/MM/dd"),
            @Mapping(target = "categories", ignore = true),
            @Mapping(target = "tags", ignore = true)
    })
    public abstract PostIndexVO convertToPostIndexVo(PostBO postBOs);

    @AfterMapping
    protected void setCategories(PostBO postBO, @MappingTarget PostIndexVO postIndexVO){
        List<Category> categories = postBO.getCategories();
        List<String> ca_collect = categories.stream().map(Category::getName).collect(Collectors.toList());
        String categories_str = String.join(", ", ca_collect);
        postIndexVO.setCategories(categories_str);

        List<Tag> tags = postBO.getTags();
        List<String> tag_collect = tags.stream().map(Tag::getName).collect(Collectors.toList());
        String tag_str = String.join(", ", tag_collect);
        postIndexVO.setTags(tag_str);
    }
}
