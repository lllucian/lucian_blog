package com.lucian.lucian_blog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lucian.lucian_blog.dto.post.PostDTO;
import com.lucian.lucian_blog.entity.Post;

public interface PostService extends IService<Post> {
    Page<PostDTO> list(Page<PostDTO> page);
}
