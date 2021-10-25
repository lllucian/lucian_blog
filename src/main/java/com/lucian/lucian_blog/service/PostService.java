package com.lucian.lucian_blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lucian.lucian_blog.bean.vo.PostIndexVO;
import com.lucian.lucian_blog.query_wrapper.PostQuery;

public interface PostService {
    IPage<PostIndexVO> queryListByPage(PostQuery postQuery);
}
