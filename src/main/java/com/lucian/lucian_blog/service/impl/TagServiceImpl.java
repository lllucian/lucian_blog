package com.lucian.lucian_blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lucian.lucian_blog.bean.entity.Tag;
import com.lucian.lucian_blog.service.TagService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

@Service
public class TagServiceImpl implements TagService {

    /**
     * 插入（批量）
     *
     * @param entityList 实体对象集合
     * @param batchSize  插入批次数量
     */
    @Override
    public boolean saveBatch(Collection<Tag> entityList, int batchSize) {
        return false;
    }

    /**
     * 批量修改插入
     *
     * @param entityList 实体对象集合
     * @param batchSize  每次的数量
     */
    @Override
    public boolean saveOrUpdateBatch(Collection<Tag> entityList, int batchSize) {
        return false;
    }

    /**
     * 根据ID 批量更新
     *
     * @param entityList 实体对象集合
     * @param batchSize  更新批次数量
     */
    @Override
    public boolean updateBatchById(Collection<Tag> entityList, int batchSize) {
        return false;
    }

    /**
     * TableId 注解存在更新记录，否插入一条记录
     *
     * @param entity 实体对象
     */
    @Override
    public boolean saveOrUpdate(Tag entity) {
        return false;
    }

    /**
     * 根据 Wrapper，查询一条记录
     *
     * @param queryWrapper 实体对象封装操作类 {@link QueryWrapper}
     * @param throwEx      有多个 result 是否抛出异常
     */
    @Override
    public Tag getOne(Wrapper<Tag> queryWrapper, boolean throwEx) {
        return null;
    }

    /**
     * 根据 Wrapper，查询一条记录
     *
     * @param queryWrapper 实体对象封装操作类 {@link QueryWrapper}
     */
    @Override
    public Map<String, Object> getMap(Wrapper<Tag> queryWrapper) {
        return null;
    }

    /**
     * 根据 Wrapper，查询一条记录
     *
     * @param queryWrapper 实体对象封装操作类 {@link QueryWrapper}
     * @param mapper       转换函数
     */
    @Override
    public <V> V getObj(Wrapper<Tag> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    /**
     * 获取对应 entity 的 BaseMapper
     *
     * @return BaseMapper
     */
    @Override
    public BaseMapper<Tag> getBaseMapper() {
        return null;
    }

    /**
     * 获取 entity 的 class
     *
     * @return {@link Class<T>}
     */
    @Override
    public Class<Tag> getEntityClass() {
        return null;
    }
}
