package com.lucian.lucian_blog.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucian.lucian_blog.bean.bo.PostBO;
import com.lucian.lucian_blog.bean.translater.PostBO2PostIndexVO;
import com.lucian.lucian_blog.bean.vo.PostIndexVO;
import com.lucian.lucian_blog.dao.PostDao;
import com.lucian.lucian_blog.query_wrapper.PostQuery;
import com.lucian.lucian_blog.service.PostService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostDao postDao;

    public IPage<PostIndexVO> queryListByPage(PostQuery postQuery){
        Page<PostBO> page = new Page<>(postQuery.getCurrentPage(), postQuery.getLimit());
        IPage<PostBO> pageBOPage = postDao.queryConditionsByPage(page, postQuery.getQueryWrapper());
        IPage<PostIndexVO> postIndexVOIPage = new Page<>();
        BeanUtils.copyProperties(pageBOPage, postIndexVOIPage);
        List<PostBO> records = pageBOPage.getRecords();
        List<PostIndexVO> indexVOS = new ArrayList<>();
        records.forEach(record -> {
            PostBO2PostIndexVO.translater(record);
        });
        return postIndexVOIPage;
    }
}
