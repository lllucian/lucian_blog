package com.lucian.lucian_blog.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucian.lucian_blog.bean.bo.PostBO;


public interface PostDao extends BaseMapper {
    IPage<PostBO> queryConditionsByPage(Page page, Wrapper wrapper);
}
