package com.lucian.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lucian.back.dao.UploadFileDao;
import com.lucian.back.service.UploadFileService;
import com.lucian.common.bean.entity.UploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

@Service
public class UploadFileServiceImpl implements UploadFileService {

    //@Autowired
    UploadFileDao uploadFileDao;

    @Autowired
    public void setFileUploadDao(UploadFileDao uploadFileDao){
        this.uploadFileDao = uploadFileDao;
    }

    @Override
    public boolean saveBatch(Collection<UploadFile> entityList, int batchSize) {
        if (entityList.size() == 0) return false;
        AtomicInteger successCount = new AtomicInteger();
        entityList.forEach(list -> successCount.addAndGet(uploadFileDao.insert(list)));
        return batchSize == successCount.get();
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<UploadFile> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<UploadFile> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(UploadFile entity) {
        return false;
    }

    @Override
    public UploadFile getOne(Wrapper<UploadFile> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper<UploadFile> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<UploadFile> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public BaseMapper<UploadFile> getBaseMapper() {
        return uploadFileDao;
    }

    @Override
    public Class<UploadFile> getEntityClass() {
        return UploadFile.class;
    }
}
