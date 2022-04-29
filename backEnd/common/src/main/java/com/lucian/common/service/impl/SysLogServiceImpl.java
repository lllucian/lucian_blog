package com.lucian.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucian.common.bean.entity.SysLog;
import com.lucian.common.dao.SysLogDao;
import com.lucian.common.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

/**
 * @author lingxiangdeng
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogDao, SysLog> implements SysLogService {

    SysLogDao sysLogDao;

    @Autowired
    public void setSysLogDao(SysLogDao sysLogDao) {
        this.sysLogDao = sysLogDao;
    }

    @Override
    public boolean saveBatch(Collection<SysLog> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<SysLog> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<SysLog> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(SysLog entity) {
        return false;
    }

    @Override
    public SysLog getOne(Wrapper<SysLog> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper<SysLog> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<SysLog> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public Class<SysLog> getEntityClass() {
        return SysLog.class;
    }
}
