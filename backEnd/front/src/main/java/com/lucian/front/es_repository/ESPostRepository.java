package com.lucian.front.es_repository;

import com.lucian.common.bean.es_entity.EsPostEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * @author denglingxiang
 * @date 2022/05/26 19:12
 **/
public interface ESPostRepository extends CrudRepository<EsPostEntity, String> {
}
