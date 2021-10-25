package com.lucian.lucian_blog.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucian.lucian_blog.bean.bo.PostBO;
import com.lucian.lucian_blog.bean.translater.PostBO2PostIndexVO;
import com.lucian.lucian_blog.bean.vo.PostIndexVO;
import com.lucian.lucian_blog.dao.PostDao;
import com.lucian.lucian_blog.query_wrapper.PostQuery;
import com.lucian.lucian_blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostDao postDao;

    @Autowired
    PostBO2PostIndexVO postBO2PostIndexVO;

    public IPage<PostIndexVO> queryListByPage(PostQuery postQuery){
        Page<PostBO> page = new Page<>(postQuery.getCurrentPage(), postQuery.getLimit());
        IPage<PostBO> pageBOPage = postDao.queryConditionsByPage(page, postQuery.getQueryWrapper());
        IPage<PostIndexVO> postIndexVOIPage = pageBOPage.convert(postBO -> postBO2PostIndexVO.convertToPostIndexVo(postBO));
        return postIndexVOIPage;
    }
}
