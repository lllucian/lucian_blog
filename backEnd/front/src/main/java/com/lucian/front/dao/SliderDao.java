package com.lucian.front.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.lucian.common.bean.entity.Post;
import com.lucian.common.bean.entity.TopPost;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author denglingxiang
 * @date 2022/05/13 16:42
 **/
public interface SliderDao extends BaseMapper<TopPost> {
    List<Post> list(@Param(Constants.WRAPPER) Wrapper wrapper);
}
