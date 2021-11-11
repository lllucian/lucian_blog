package com.lucian.lucian_blog.manager;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucian.lucian_blog.bean.entity.Tag;
import com.lucian.lucian_blog.bean.vo.TagIndexVO;
import com.lucian.lucian_blog.query_wrapper.TagQuery;
import com.lucian.lucian_blog.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TagManager {
    @Autowired
    TagService tagService;

    public Page<TagIndexVO> queryListByPage(TagQuery tagQuery){
        Page<Tag> page = new Page<>(tagQuery.getCurrentPage(), tagQuery.getLimit());
        Page<Tag> tags = tagService.page(page, tagQuery.getQueryWrapper());
        Page<TagIndexVO> tagIndexVO = new Page<>();
        BeanUtils.copyProperties(tags, tagIndexVO);
        return tagIndexVO;
    }
}
