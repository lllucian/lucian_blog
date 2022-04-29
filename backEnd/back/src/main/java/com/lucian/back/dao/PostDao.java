package com.lucian.back.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucian.back.bean.bo.PostBO;
import com.lucian.common.bean.entity.Post;
import org.apache.ibatis.annotations.Param;


/**
 * @author lingxiangdeng
 */
public interface PostDao extends BaseMapper<Post> {
    IPage<PostBO> queryConditionsByPage(Page<PostBO> page, @Param(Constants.WRAPPER) Wrapper wrapper);

    PostBO queryPostDetail(@Param("id") Integer id);
}
