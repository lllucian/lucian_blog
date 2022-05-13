package com.lucian.front.manager;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lucian.common.bean.entity.Post;
import com.lucian.common.bean.entity.TopPost;
import com.lucian.front.bean.translate.Post2IndexVO;
import com.lucian.front.bean.vo.BlogIndexVO;
import com.lucian.front.service.BlogService;
import com.lucian.front.service.SliderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author denglingxiang
 * @date 2022/05/13 16:48
 **/
@Component
public class SliderManager {

    SliderService sliderService;

    @Autowired
    public void setSliderService(SliderService sliderService) {
        this.sliderService = sliderService;
    }

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

    /**
     * 获取轮播图数据
     * @return 轮播图数据
     */
    public List<BlogIndexVO> list(){
        QueryWrapper<TopPost> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("top_posts.is_hidden", false);
        queryWrapper.orderByAsc("top_posts.sort");
        queryWrapper.isNull("posts.deleted_at");
        List<Post> posts = sliderService.list(queryWrapper);
        return post2IndexVO.convertToBlogIndex(posts);
    }
}
