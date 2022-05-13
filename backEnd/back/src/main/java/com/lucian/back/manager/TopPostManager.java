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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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

    Post2SelectVO post2SelectVO;

    @Autowired
    public void setPost2SelectVO(Post2SelectVO post2SelectVO) {
        this.post2SelectVO = post2SelectVO;
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

    /**
     * 改变轮播图的顺序
     * @param changeTopPostSortParam 轮播图id集合
     * @return 是否改变顺序成功
     */
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

    /**
     * 编辑轮播图
     * @param topPostId 轮播图id
     * @param topPostParam 轮播图参数
     * @return 是否更新成功
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean updateObject(Integer topPostId, TopPostParam topPostParam) {
        System.out.println(topPostParam);
        TopPost topPost = topPostService.getById(topPostId);
        if (Objects.isNull(topPost)){
            return false;
        }
        BeanUtils.copyProperties(topPostParam, topPost);
        return topPostService.updateById(topPost);
    }

    /**
     * 获取已选择的post信息
     * @param postId post id
     * @return post信息
     */
    public PostSelectDataVO postSelected(Integer postId){
        Post post = postService.getById(postId);
        return post2SelectVO.translate(post);
    }

    /**
     * 获取没有选择的post信息（除自身选过的post）
     * @param id topPost id
     * @param query 关键词
     * @return 筛选过的post信息
     */
    public List<PostSelectDataVO> postList(Integer id, String query){
        QueryWrapper<TopPost> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne("id", id);
        List<Integer> selectedPostIds = topPostService.list(queryWrapper).stream().map(TopPost::getPostId).collect(Collectors.toList());
        QueryWrapper<Post> postQueryWrapper = new QueryWrapper<>();
        if (!selectedPostIds.isEmpty()){
            postQueryWrapper.notIn("id", selectedPostIds);
        }
        postQueryWrapper.like("title", query);
        List<Post> effectivePost = postService.list(postQueryWrapper);
        return post2SelectVO.translate(effectivePost);
    }
}
