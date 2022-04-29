package com.lucian.back.bean.translate;

import com.lucian.back.bean.bo.PostBO;
import com.lucian.back.bean.vo.PostFormVO;
import com.lucian.common.bean.entity.Category;
import com.lucian.common.bean.entity.Tag;
import org.mapstruct.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lingxiangdeng
 */
@Mapper(componentModel = "spring")
public abstract class PostBO2PostFormVO {
    /**
     * 将数据库里查询出的对象转换为form内容
     * @param postBO 数据库中post杂糅的对象
     * @return form内容
     */
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

    /**
     * 将无法用mapStruct转换的内容进行手动指定
     * @param postBO post数据库中的内容
     * @param postFormVO post 表单中的内容
     */
    @AfterMapping
    protected void setCategoriesAndTags(PostBO postBO, @MappingTarget PostFormVO postFormVO){
        List<Category> categories = postBO.getCategories();
        List<Integer> caCollect = categories.stream().map(Category::getId).collect(Collectors.toList());
        postFormVO.setCategories(caCollect);

        List<Tag> tags = postBO.getTags();
        List<Integer> tagCollect = tags.stream().map(Tag::getId).collect(Collectors.toList());
        postFormVO.setTags(tagCollect);
    }
}
