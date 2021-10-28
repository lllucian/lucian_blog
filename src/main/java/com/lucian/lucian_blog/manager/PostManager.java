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
    @Transactional
    public boolean update(Integer postId, PostParam postParam){
        Post post = new Post();
        BeanUtils.copyProperties(postParam, post);
        post.setId(postId);
        int i = postDao.updateById(post);
        if (i == 0) return false;
        updateCategories(post, postParam);
        updateTags(post, postParam);
        return true;
    }

    private void updateCategories(Post post, PostParam postParam){
        QueryWrapper<PostCategory> postCategoryQueryWrapper = new QueryWrapper<>();
        postCategoryQueryWrapper.eq("post_id", post.getId());
        if (postParam.getCategories() != null) {
            // 有效的分类
            List<Integer> enableCategoryIds = categoryDao.selectBatchIds(postParam.getCategories()).stream().map(BaseEntity::getId).collect(Collectors.toList());
            // 关联表中的分类
            List<Integer> assocPostCategoryIds = postCategoryDao.selectList(postCategoryQueryWrapper).stream().map(PostCategory::getCategoryId).collect(Collectors.toList());
            // 删除关联表中的ids
            List<Integer> deleteCategoryIds = assocPostCategoryIds.stream().filter(postCategoryId -> !enableCategoryIds.contains(postCategoryId)).collect(Collectors.toList());
            if (deleteCategoryIds.size() != 0) {
                QueryWrapper<PostCategory> deleteConditions = new QueryWrapper<>();
                deleteConditions.eq("post_id", post.getId());
                deleteConditions.in("category_id", deleteCategoryIds);
                postCategoryDao.delete(deleteConditions);
            }
            // 增加关联表中的id
            List<Integer> shouldAddCategoryIds = enableCategoryIds.stream().filter(enableCategoryId -> !assocPostCategoryIds.contains(enableCategoryId)).collect(Collectors.toList());
            shouldAddCategoryIds.forEach(categoryId -> postCategoryDao.insert(new PostCategory(post.getId(), categoryId)));
        } else {
            postCategoryDao.delete(postCategoryQueryWrapper);
        }
    }

    private void updateTags(Post post, PostParam postParam){
        QueryWrapper<PostTag> postTagQueryWrapper = new QueryWrapper<>();
        postTagQueryWrapper.eq("post_id", post.getId());
        if (postParam.getTags() != null) {
            // 有效的分类
            List<Integer> enableTagIds = tagDao.selectBatchIds(postParam.getTags()).stream().map(BaseEntity::getId).collect(Collectors.toList());
            // 关联表中的分类
            List<Integer> assocPostTagIds = postTagDao.selectList(postTagQueryWrapper).stream().map(PostTag::getTagId).collect(Collectors.toList());
            // 删除关联表中的ids
            List<Integer> deleteTagIds = assocPostTagIds.stream().filter(postTagId -> !enableTagIds.contains(postTagId)).collect(Collectors.toList());
            if (deleteTagIds.size() != 0) {
                QueryWrapper<PostTag> deleteConditions = new QueryWrapper<>();
                deleteConditions.eq("post_id", post.getId());
                deleteConditions.in("tag_id", deleteTagIds);
                postTagDao.delete(deleteConditions);
            }
            // 增加关联表中的id
            List<Integer> shouldAddTagIds = enableTagIds.stream().filter(enableTagId -> !assocPostTagIds.contains(enableTagId)).collect(Collectors.toList());
            shouldAddTagIds.forEach(tagId -> postCategoryDao.insert(new PostCategory(post.getId(), tagId)));
        } else {
            postTagDao.delete(postTagQueryWrapper);
        }
    }
}
