package com.lucian.back.bean.translate;

import cn.hutool.extra.spring.SpringUtil;
import com.lucian.back.bean.vo.TopPostIndexVO;
import com.lucian.back.service.PostService;
import com.lucian.common.bean.entity.Post;
import com.lucian.common.bean.entity.TopPost;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author lingxiangdeng
 */
@Mapper(componentModel = "spring")
public abstract class TopPost2IndexVO {

    /**
     * bean转vo
     * @param topPost topPost bean
     * @return TopPost vo
     */
    public abstract TopPostIndexVO translate(TopPost topPost);

    /**
     * bean集合转vo集合
     * @param topPost topPost bean集合
     * @return TopPost vo集合
     */
    public abstract List<TopPostIndexVO> translate(List<TopPost> topPost);

    /**
     * 设置 文章标题
     * @param topPostIndexList TopPostIndexVO集合
     */
    @AfterMapping
    public void setPostDetail(@MappingTarget List<TopPostIndexVO> topPostIndexList) {
        // 将postId提取出来，从而去查询post.title
        Map<String, ArrayList<TopPostIndexVO>> postIdWithTopPostIndex = topPostIndexList.stream().filter(topPostIndexVO -> Objects.nonNull(topPostIndexVO.getPostId()))
                .collect(Collectors.toMap(TopPostIndexVO::getPostId, topPost -> new ArrayList<TopPostIndexVO>() {{
                            add(topPost);
                        }},
                        (oldValue, newValue) -> {
                            oldValue.addAll(newValue);
                            return oldValue;
                        }));
        //提取出postId
        Set<String> postIds = postIdWithTopPostIndex.keySet();
        if (postIds.size() == 0){
            return;
        }
        PostService postService = SpringUtil.getBean(PostService.class);
        // 用TopPost中的postId去获取post信息
        Map<String, String> postIdWithTitleMap = postService.listByIds(postIds).stream().collect(Collectors.toMap(Post::getId, Post::getTitle));
        postIdWithTitleMap.forEach((postId, postTitle) -> {
            postIdWithTopPostIndex.get(postId).forEach(topPost -> topPost.setPostTitle(postTitle));
        });
    }
}
