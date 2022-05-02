package com.lucian.back.bean.translate;

import com.lucian.back.bean.vo.PostSelectDataVO;
import com.lucian.common.bean.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel="spring")
public interface Post2SelectVO {

    /**
     * 获取select下拉框数据
     * @param post 文章
     * @return select下拉框数据
     */
    @Mapping(source = "id", target = "value")
    @Mapping(source = "title", target ="label")
    PostSelectDataVO translate(Post post);

    /**
     * 获取select下拉框数据
     * @param posts 文章
     * @return select下拉框数据
     */
    List<PostSelectDataVO> translate(List<Post> posts);
}
