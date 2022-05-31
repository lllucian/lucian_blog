package com.lucian.front.es_service;

import com.lucian.common.bean.es_entity.EsPostEntity;
import org.springframework.data.elasticsearch.core.SearchHit;

import java.util.List;

/**
 * @author denglingxiang
 * @date 2022/05/29 13:11
 **/
public interface ESPostService {

    /**
     * es blog关键词检索
     * @param keyword 关键词
     * @return blog 集合
     */
    List<SearchHit<EsPostEntity>> keywordSearch(String keyword);

}
