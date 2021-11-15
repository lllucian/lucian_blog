package com.lucian.lucian_blog.manager;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucian.lucian_blog.bean.entity.Tag;
import com.lucian.lucian_blog.bean.translater.Tag2TagIndexVO;
import com.lucian.lucian_blog.bean.vo.TagFormVO;
import com.lucian.lucian_blog.bean.vo.TagIndexVO;
import com.lucian.lucian_blog.form_parm.TagParam;
import com.lucian.lucian_blog.query_wrapper.TagQuery;
import com.lucian.lucian_blog.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TagManager {
    @Autowired
    TagService tagService;

    // entity转vo
    @Autowired
    Tag2TagIndexVO tag2TagIndexVO;

    /**
     * 获取一览
     * @param tagQuery 查询条件
     * @return 分页标签对象
     */
    public IPage<TagIndexVO> queryListByPage(TagQuery tagQuery){
        Page<Tag> page = new Page<>(tagQuery.getCurrentPage(), tagQuery.getLimit());
        Page<Tag> tags = tagService.page(page, tagQuery.getQueryWrapper());
        return tags.convert(tag -> tag2TagIndexVO.translate(tag));
    }

    /**
     * 新建标签
     * @param tagParam 表单数据
     * @return 是否创建成功
     */
    @Transactional
    public boolean createRecord(TagParam tagParam){
        Tag tag = new Tag();
        // 数据转换
        BeanUtils.copyProperties(tagParam, tag);
        return tagService.save(tag);
    }

    /**
     * 查找标签
     * @param id 标签id
     * @return 标签对象
     */
    public TagFormVO findOne(Integer id){
        if (id == null) return null;
        Tag tag = tagService.getById(id);
        TagFormVO tagFormVO = new TagFormVO();
        // 数据转换
        BeanUtils.copyProperties(tag, tagFormVO);
        return tagFormVO;
    }

    /**
     * 更新标签
     * @param id 标签id
     * @param tagParam 表单数据
     * @return 是否更新成功
     */
    public Boolean updateOne(Integer id, TagParam tagParam){
        if (id == null) return false;
        Tag tag = tagService.getById(id);
        if (tag == null) return false;
        //数据转换
        BeanUtils.copyProperties(tagParam, tag);
        return tagService.updateById(tag);
    }

    /**
     * 删除标签
     * @param id 标签id
     * @return 是否删除成功
     */
    public Boolean deleteOne(Integer id){
        if (id == null) return false;
        Tag tag = tagService.getById(id);
        if (tag == null) return false;
        return tagService.removeById(id);
    }
}
