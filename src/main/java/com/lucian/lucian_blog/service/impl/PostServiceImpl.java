package com.lucian.lucian_blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucian.lucian_blog.dao.PostDao;
import com.lucian.lucian_blog.dto.post.PostDTO;
import com.lucian.lucian_blog.entity.Post;
import com.lucian.lucian_blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostDao postDao;

    public Page<PostDTO> list(Page<PostDTO> page) {
        return page.setRecords(postDao.list(page));
    }

    @Override
    public boolean saveBatch(Collection<Post> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<Post> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<Post> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(Post entity) {
        return false;
    }

    @Override
    public Post getOne(Wrapper<Post> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper<Post> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<Post> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public BaseMapper<Post> getBaseMapper() {
        return null;
    }

    @Override
    public Class<Post> getEntityClass() {
        return null;
    }
}
