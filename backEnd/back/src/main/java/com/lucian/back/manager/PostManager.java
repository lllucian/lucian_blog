package com.lucian.back.manager;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucian.back.bean.bo.PostBO;
import com.lucian.back.bean.translate.PostBO2PostFormVO;
import com.lucian.back.bean.translate.PostBO2PostIndexVO;
import com.lucian.back.bean.vo.PostFormVO;
import com.lucian.back.bean.vo.PostIndexVO;
import com.lucian.back.dao.*;
import com.lucian.back.form_parm.PostParam;
import com.lucian.back.query_wrapper.PostQuery;
import com.lucian.back.service.PostService;
import com.lucian.common.bean.entity.*;
import com.lucian.common.utils.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lingxiangdeng
 */
@Component
public class PostManager {
    PostDao postDao;

    @Autowired
    public void setPostDao(PostDao postDao) {
        this.postDao = postDao;
    }

    PostService postService;

    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    PostBO2PostIndexVO postBO2PostIndexVO;

    @Autowired
    public void setPostBO2PostIndexVO(PostBO2PostIndexVO postBO2PostIndexVO) {
        this.postBO2PostIndexVO = postBO2PostIndexVO;
    }

    PostBO2PostFormVO postBO2PostFormVO;

    @Autowired
    public void setPostBO2PostFormVO(PostBO2PostFormVO postBO2PostFormVO) {
        this.postBO2PostFormVO = postBO2PostFormVO;
    }

    CategoryDao categoryDao;

    @Autowired
    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    PostCategoryDao postCategoryDao;

    @Autowired
    public void setPostCategoryDao(PostCategoryDao postCategoryDao) {
        this.postCategoryDao = postCategoryDao;
    }

    TagDao tagDao;

    @Autowired
    public void setTagDao(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    PostTagDao postTagDao;

    @Autowired
    public void setPostTagDao(PostTagDao postTagDao) {
        this.postTagDao = postTagDao;
    }

    public IPage<PostIndexVO> queryListByPage(PostQuery postQuery){
        if (postQuery == null) {
            postQuery = new PostQuery();
        }
        Page<PostBO> page = new Page<>(postQuery.getCurrent(), postQuery.getSize());
        IPage<PostBO> pageBOPage = postDao.queryConditionsByPage(page, postQuery.getQueryWrapper());
        return pageBOPage.convert(postBO -> postBO2PostIndexVO.convertToPostIndexVo(postBO));
    }

    /**
     * blog 详情
     * @param id 博客id
     * @return 博客详情
     */
    public PostFormVO postDetail(String id) {
        PostBO postBO = postDao.queryPostDetail(id);
        return postBO == null ? null : postBO2PostFormVO.postBO2PostFormVO(postBO);
    }

    /**
     *
     * @param postParam 表单数据
     * @return 是否插入成功
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean insert(PostParam postParam){
        Post post = new Post();
        post.setUserId(SecurityUtils.getCurrentUserId());
        BeanUtils.copyProperties(postParam, post);
        postDao.insert(post);
        if (postParam.getCategories() != null) {
            List<Category> categories = categoryDao.selectBatchIds(postParam.getCategories());
            categories.forEach(category -> postCategoryDao.insert(new PostCategory(post.getId(), category.getId())));
        }
        if (postParam.getTags() != null && postParam.getTags().size() > 0) {
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
    @Transactional(rollbackFor = Exception.class)
    public boolean update(String postId, PostParam postParam){
        Post post = new Post();
        BeanUtils.copyProperties(postParam, post);
        post.setId(postId);
        int i = postDao.updateById(post);
        if (i == 0) {
            return false;
        }
        updateCategories(post, postParam);
        updateTags(post, postParam);
        return true;
    }

    private void updateCategories(Post post, PostParam postParam){
        QueryWrapper<PostCategory> postCategoryQueryWrapper = new QueryWrapper<>();
        postCategoryQueryWrapper.eq("post_id", post.getId());
        if (postParam.getCategories() != null) {
            // 有效的分类
            List<String> enableCategoryIds = categoryDao.selectBatchIds(postParam.getCategories()).stream().map(BaseEntity::getId).collect(Collectors.toList());
            // 关联表中的分类
            List<String> assocPostCategoryIds = postCategoryDao.selectList(postCategoryQueryWrapper).stream().map(PostCategory::getCategoryId).collect(Collectors.toList());
            // 删除关联表中的ids
            List<String> deleteCategoryIds = assocPostCategoryIds.stream().filter(postCategoryId -> !enableCategoryIds.contains(postCategoryId)).collect(Collectors.toList());
            if (deleteCategoryIds.size() != 0) {
                QueryWrapper<PostCategory> deleteConditions = new QueryWrapper<>();
                deleteConditions.eq("post_id", post.getId());
                deleteConditions.in("category_id", deleteCategoryIds);
                postCategoryDao.delete(deleteConditions);
            }
            // 增加关联表中的id
            List<String> shouldAddCategoryIds = enableCategoryIds.stream().filter(enableCategoryId -> !assocPostCategoryIds.contains(enableCategoryId)).collect(Collectors.toList());
            shouldAddCategoryIds.forEach(categoryId -> postCategoryDao.insert(new PostCategory(post.getId(), categoryId)));
        } else {
            postCategoryDao.delete(postCategoryQueryWrapper);
        }
    }

    private void updateTags(Post post, PostParam postParam){
        QueryWrapper<PostTag> postTagQueryWrapper = new QueryWrapper<>();
        postTagQueryWrapper.eq("post_id", post.getId());
        if (postParam.getTags() != null && postParam.getTags().size() > 0) {
            // 有效的标签
            List<String> enableTagIds = tagDao.selectBatchIds(postParam.getTags()).stream().map(BaseEntity::getId).collect(Collectors.toList());
            // 关联表中的分类
            List<String> assocPostTagIds = postTagDao.selectList(postTagQueryWrapper).stream().map(PostTag::getTagId).collect(Collectors.toList());
            // 删除关联表中的ids
            List<String> deleteTagIds = assocPostTagIds.stream().filter(postTagId -> !enableTagIds.contains(postTagId)).collect(Collectors.toList());
            if (deleteTagIds.size() != 0) {
                QueryWrapper<PostTag> deleteConditions = new QueryWrapper<>();
                deleteConditions.eq("post_id", post.getId());
                deleteConditions.in("tag_id", deleteTagIds);
                postTagDao.delete(deleteConditions);
            }
            // 增加关联表中的id
            List<String> shouldAddTagIds = enableTagIds.stream().filter(enableTagId -> !assocPostTagIds.contains(enableTagId)).collect(Collectors.toList());
            shouldAddTagIds.forEach(tagId -> postTagDao.insert(new PostTag(post.getId(), tagId)));
        } else {
            postTagDao.delete(postTagQueryWrapper);
        }
    }

    /**
     * 删除文章 及其关联表中的数据
     * @param id blog id
     * @return 是否删除成功
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean deletePost(String id){
        Post post = postDao.selectById(id);
        if (post == null) {
            return false;
        }
        // 删除文章
        postDao.deleteById(post);
        // 删除 category关联表
        QueryWrapper<PostCategory> postCategoryQueryWrapper = new QueryWrapper<>();
        postCategoryQueryWrapper.eq("post_id", id);
        postCategoryDao.delete(postCategoryQueryWrapper);
        // 删除 tag 关联表
        QueryWrapper<PostTag> postTagQueryWrapper = new QueryWrapper<>();
        postCategoryQueryWrapper.eq("post_id", id);
        postTagDao.delete(postTagQueryWrapper);
        return true;
    }

}
