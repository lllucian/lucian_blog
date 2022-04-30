package com.lucian.front.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucian.common.bean.entity.Post;
import com.lucian.front.dao.BlogDao;
import com.lucian.front.service.BlogService;
import org.springframework.stereotype.Service;

/**
 * @author lingxiangdeng
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogDao, Post> implements BlogService {
}
