package com.lucian.lucian_blog.manager;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucian.lucian_blog.bean.bo.PostBO;
import com.lucian.lucian_blog.bean.entity.*;
import com.lucian.lucian_blog.bean.translater.PostBO2PostIndexVO;
import com.lucian.lucian_blog.bean.vo.PostIndexVO;
import com.lucian.lucian_blog.dao.*;
import com.lucian.lucian_blog.form_parm.PostParam;
import com.lucian.lucian_blog.query_wrapper.PostQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostManager {
    @Autowired
    PostDao postDao;

    @Autowired
    PostBO2PostIndexVO postBO2PostIndexVO;

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    PostCategoryDao postCategoryDao;

    @Autowired
    TagDao tagDao;

    @Autowired
    PostTagDao postTagDao;

    public IPage<PostIndexVO> queryListByPage(PostQuery postQuery){
        Page<PostBO> page = new Page<>(postQuery.getCurrentPage(), postQuery.getLimit());
        IPage<PostBO> pageBOPage = postDao.queryConditionsByPage(page, postQuery.getQueryWrapper());
        IPage<PostIndexVO> postIndexVOIPage = pageBOPage.convert(postBO -> postBO2PostIndexVO.convertToPostIndexVo(postBO));
        return postIndexVOIPage;
    }

    /**
     *
     * @param postParam 表单数据
     * @return 是否插入成功
     */
    @Transactional
    public boolean insert(PostParam postParam){
        Post post = new Post();
        BeanUtils.copyProperties(postParam, post);
        postDao.insert(post);
        if (postParam.getCategories() != null) {
            List<Category> categories = categoryDao.selectBatchIds(postParam.getCategories());
            categories.forEach(category -> postCategoryDao.insert(new PostCategory(post.getId(), category.getId())));
        }
        if (postParam.getTags() != null) {
            List<Tag> tags = tagDao.selectBatchIds(postParam.getTags());
            tags.forEach(tag -> postTagDao.insert(new PostTag(post.getId(), tag.getId())));
        }
        return true;
    }

    /**
     *
     * @param postParam 表单数据
     * @return 是否插入成功
     */
    public boolean update(Integer postId, PostParam postParam){
        Post post = new Post();
        BeanUtils.copyProperties(postParam, post);
        post.setId(postId);
        int i = postDao.updateById(post);
        if (i == 0) return false;
        QueryWrapper<PostCategory> postCategoryQueryWrapper = new QueryWrapper<>();
        postCategoryQueryWrapper.eq("post_id", post.getId());
        if (postParam.getCategories() != null) {
            // 有效的分类
            List<Integer> enableCategoryIds = categoryDao.selectBatchIds(postParam.getCategories()).stream().map(BaseEntity::getId).collect(Collectors.toList());
            // 关联表中的分类
            List<PostCategory> postCategories = postCategoryDao.selectList(postCategoryQueryWrapper);

        } else {
            postCategoryDao.delete(postCategoryQueryWrapper);
        }
        // 要删除的分类id
//        if (postParam.getCategories() != null) {
//            List<Integer> categoryIds = categoryDao.selectBatchIds(postParam.getCategories())
//                    .stream().map(BaseEntity::getId).collect(Collectors.toList());
//            // 查出文章下的所有分类
//            QueryWrapper<PostCategory> postCategoryQueryWrapper = new QueryWrapper<>();
//            postCategoryQueryWrapper.eq("post_id", postId);
//            List<PostCategory> postCategories = postCategoryDao.selectList(postCategoryQueryWrapper);
//            // 如果存在则不添加
//            if (postCategories != null){
//                List<Integer> collect = postCategories.stream().map(PostCategory::getCategoryId).collect(Collectors.toList());
//                categoryIds = categoryIds.stream().filter(id -> !collect.contains(id)).collect(Collectors.toList());
//            }
//            // 如果数据库不存在则添加
//            categoryIds.forEach(id -> postCategoryDao.insert(new PostCategory(post.getId(), id)));
//            // 如果数据库存在传过来的值不存在则删除
//
//        }
        if (postParam.getTags() != null) {
            List<Tag> tags = tagDao.selectBatchIds(postParam.getTags());
            tags.forEach(tag -> postTagDao.insert(new PostTag(post.getId(), tag.getId())));
        }
        return true;
    }
}
