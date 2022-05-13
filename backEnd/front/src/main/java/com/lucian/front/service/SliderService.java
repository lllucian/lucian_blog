package com.lucian.front.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lucian.common.bean.entity.Post;
import com.lucian.common.bean.entity.TopPost;

import java.util.List;

/**
 * @author denglingxiang
 * @date 2022/05/13 16:44
 **/
public interface SliderService extends IService<TopPost> {
    List<Post> list(QueryWrapper<TopPost> queryWrapper);
}
