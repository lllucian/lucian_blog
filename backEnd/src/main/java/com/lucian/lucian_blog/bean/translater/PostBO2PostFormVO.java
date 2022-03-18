package com.lucian.lucian_blog.bean.translater;

import com.lucian.lucian_blog.bean.bo.PostBO;
import com.lucian.lucian_blog.bean.entity.Category;
import com.lucian.lucian_blog.bean.entity.Tag;
import com.lucian.lucian_blog.bean.vo.PostFormVO;
import org.mapstruct.*;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class PostBO2PostFormVO {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "post.title", target = "title"),
            @Mapping(source = "post.content", target = "content"),
            @Mapping(source = "post.description", target = "description"),
            @Mapping(source = "post.sort", target = "sort"),
            @Mapping(source = "post.createdAt", target = "createdAt", dateFormat = "yyyy/MM/dd HH:mm:ss"),
            @Mapping(target = "updatedAt", source = "post.updatedAt", dateFormat = "yyyy/MM/dd HH:mm:ss"),
            @Mapping(target = "categories", ignore = true),
            @Mapping(target = "tags", ignore = true),
            @Mapping(target = "fileUpload", source="post.uploadFileId")
    })
    public abstract PostFormVO postBO2PostFormVO(PostBO postBO);

    @AfterMapping
    protected void setCategoriesAndTags(PostBO postBO, @MappingTarget PostFormVO postFormVO){
        List<Category> categories = postBO.getCategories();
        List<Integer> ca_collect = categories.stream().map(Category::getId).collect(Collectors.toList());
        postFormVO.setCategories(ca_collect);

        List<Tag> tags = postBO.getTags();
        List<Integer> tag_collect = tags.stream().map(Tag::getId).collect(Collectors.toList());
        postFormVO.setTags(tag_collect);
    }
}
