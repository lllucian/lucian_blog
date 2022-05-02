package com.lucian.back.manager;

import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lucian.back.bean.translate.Post2SelectVO;
import com.lucian.back.bean.translate.TopPost2IndexVO;
import com.lucian.back.bean.vo.PostSelectDataVO;
import com.lucian.back.bean.vo.TopPostIndexVO;
import com.lucian.back.form_parm.ChangeTopPostSortParam;
import com.lucian.back.form_parm.TopPostParam;
import com.lucian.back.query_wrapper.PostSelectQuery;
import com.lucian.back.service.PostService;
import com.lucian.back.service.TopPostService;
import com.lucian.common.bean.entity.Post;
import com.lucian.common.bean.entity.TopPost;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author lingxiangdeng
 */
@Component
public class TopPostManager {

    TopPostService topPostService;

    @Autowired
    public void setTopPostService(TopPostService topPostService) {
        this.topPostService = topPostService;
    }

    TopPost2IndexVO topPost2IndexVO;

    @Autowired
    public void setTopPost2IndexVO(TopPost2IndexVO topPost2IndexVO) {
        this.topPost2IndexVO = topPost2IndexVO;
    }

    PostService postService;

    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    public List<TopPostIndexVO> list() {
        QueryWrapper<TopPost> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");
        List<TopPost> topPostList = topPostService.list(queryWrapper);
        return topPost2IndexVO.translate(topPostList);
    }

    public boolean createRecord(TopPostParam topPostParam){
        TopPost topPost = new TopPost();
        BeanUtils.copyProperties(topPostParam, topPost);
        topPost.setSort((int) (topPostService.count() + 1));
        return topPostService.save(topPost);
    }

    /**
     * 新建轮播图中的select search
     * @param postSelectQuery select search
     * @return PostSelectDataVO 集合
     */
    public List<PostSelectDataVO> selectSearch(PostSelectQuery postSelectQuery){
        if (postSelectQuery == null) {
            postSelectQuery = new PostSelectQuery();
        }
        QueryWrapper<Post> queryWrapper = postSelectQuery.getQueryWrapper();
        List<Integer> postIds = topPostService.list().stream().map(TopPost::getPostId).filter(Objects::nonNull).collect(Collectors.toList());
        if (postIds.size() > 0) {
            queryWrapper.notIn("id", postIds);
        }
        List<Post> list = postService.list(queryWrapper);

        List<PostSelectDataVO> searchData = new ArrayList<>();
        if (list.size() == 0) {
            return searchData;
        }
        Post2SelectVO postTranslate = SpringUtil.getBean(Post2SelectVO.class);
        return postTranslate.translate(list);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean changeSortNumber(List<ChangeTopPostSortParam> changeTopPostSortParam){
        // 将id通过sort排序
        List<ChangeTopPostSortParam> collect = changeTopPostSortParam.stream().filter(sort -> Objects.nonNull(sort.getId())).collect(Collectors.toList());
        Map<Integer, TopPost> postMap = topPostService.list().stream().collect(Collectors.toMap(TopPost::getId, topPost -> topPost));
        int sortNum = 0;
        // 开始排序
        for (ChangeTopPostSortParam topPostSortParam : collect) {
            if (postMap.containsKey(topPostSortParam.getId())) {
                sortNum++;
                TopPost topPost = postMap.get(topPostSortParam.getId());
                topPost.setSort(sortNum);
                topPost.setCreatedAt(null);
                topPost.setUpdatedAt(null);
                topPostService.updateById(topPost);
            }
        }
        return true;
    }
}
