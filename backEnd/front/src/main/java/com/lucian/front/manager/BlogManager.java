package com.lucian.front.manager;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucian.common.bean.entity.Post;
import com.lucian.front.bean.translate.Post2IndexVO;
import com.lucian.front.bean.vo.BlogIndexVO;
import com.lucian.front.service.BlogService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author lingxiangdeng
 */
@Component
public class BlogManager {

    BlogService blogService;

    @Autowired
    public void setBlogService(BlogService blogService) {
        this.blogService = blogService;
    }

    Post2IndexVO post2IndexVO;

    @Autowired
    public void setPost2IndexVO(Post2IndexVO post2IndexVO) {
        this.post2IndexVO = post2IndexVO;
    }

    public IPage<BlogIndexVO> list(Integer currentPage){
        Page<Post> page = new Page<>(currentPage, 10);
        Page<Post> posts = blogService.page(page);
        Page<BlogIndexVO> blogData = new Page<>();
        BeanUtils.copyProperties(page, blogData);
        blogData.setRecords(post2IndexVO.convertToBlogIndex(posts.getRecords()));
        return blogData;
    }
}
