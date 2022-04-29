package com.lucian.back.manager;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucian.back.bean.translate.Tag2SelectVO;
import com.lucian.back.bean.translate.Tag2TagIndexVO;
import com.lucian.back.bean.vo.TagFormVO;
import com.lucian.back.bean.vo.TagIndexVO;
import com.lucian.back.bean.vo.TagSelectDataVO;
import com.lucian.back.form_parm.TagParam;
import com.lucian.back.query_wrapper.TagQuery;
import com.lucian.back.query_wrapper.TagSelectQuery;
import com.lucian.back.service.TagService;
import com.lucian.common.bean.entity.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lingxiangdeng
 */
@Component
public class TagManager {
    @Autowired
    TagService tagService;

    // entity转vo
    @Autowired
    Tag2TagIndexVO tag2TagIndexVO;

    @Autowired
    Tag2SelectVO tag2SelectVO;

    /**
     * 获取一览
     * @param tagQuery 查询条件
     * @return 分页标签对象
     */
    public IPage<TagIndexVO> queryListByPage(TagQuery tagQuery){
        Page<Tag> page = new Page<>(tagQuery.getCurrent(), tagQuery.getSize());
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
        if (id == null) {
            return null;
        }
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
        if (id == null) {
            return false;
        }
        Tag tag = tagService.getById(id);
        if (tag == null) {
            return false;
        }
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

    /**
     * select 检索框
     * @param tagSelectQuery 检索条件
     * @return 符合检索的内容
     */
    public List<TagSelectDataVO> selectSearch(TagSelectQuery tagSelectQuery){
        if (tagSelectQuery == null) {
            tagSelectQuery = new TagSelectQuery();
        }
        List<Tag> list = tagService.list(tagSelectQuery.getQueryWrapper());
        List<TagSelectDataVO> searchData = new ArrayList<>();
        if (list.size() == 0) {
            return searchData;
        }
        return tag2SelectVO.tranTag2SelectVOList(list);
    }

    /**
     * 获取被选中的option
     * @param ids 分类id
     * @return 被选中option
     */
    public List<TagSelectDataVO> getSelectData(List<Integer> ids){
        if (ids == null && ids.size() != 0) {
            return null;
        }
        return tag2SelectVO.tranTag2SelectVOList(tagService.listByIds(ids));
    }

}
