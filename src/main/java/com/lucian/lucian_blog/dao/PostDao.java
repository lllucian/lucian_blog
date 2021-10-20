package com.lucian.lucian_blog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucian.lucian_blog.dto.post.PostDTO;
import com.lucian.lucian_blog.entity.Post;
import javafx.scene.control.Pagination;

import java.util.List;

public interface PostDao extends BaseMapper<Post> {
    List<PostDTO> list(Page<?> page);
}
