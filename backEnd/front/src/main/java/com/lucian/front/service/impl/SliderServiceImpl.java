package com.lucian.front.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucian.common.bean.entity.Post;
import com.lucian.common.bean.entity.TopPost;
import com.lucian.front.dao.SliderDao;
import com.lucian.front.service.SliderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author denglingxiang
 * @date 2022/05/13 16:45
 **/
@Service
public class SliderServiceImpl extends ServiceImpl<SliderDao, TopPost> implements SliderService {
    SliderDao sliderDao;

    @Autowired
    public void setSliderDao(SliderDao sliderDao) {
        this.sliderDao = sliderDao;
    }

    public List<Post> list(QueryWrapper<TopPost> queryWrapper) {
        return sliderDao.list(queryWrapper);
    }
}
