package com.lucian.front.es_service.impl;

import com.lucian.common.bean.es_entity.EsPostEntity;
import com.lucian.front.es_repository.ESPostRepository;
import com.lucian.front.es_service.ESPostService;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author denglingxiang
 * @date 2022/05/29 13:12
 **/
@Service
public class ESPostServiceImpl implements ESPostService {

    ESPostRepository esPostRepository;

    @Autowired
    public void setEsPostRepository(ESPostRepository esPostRepository) {
        this.esPostRepository = esPostRepository;
    }

    ElasticsearchOperations elasticsearchOperations;

    @Autowired
    public void setElasticsearchOperations(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

    @Override
    public List<SearchHit<EsPostEntity>> keywordSearch(String keyword) {
        // 关键词空直接返回
        if (!StringUtils.hasText(keyword)) {
            return null;
        }
        keyword = keyword.trim();
        QueryBuilder multiMatchQuery = QueryBuilders.
                multiMatchQuery(keyword, "title", "description", "content");
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(multiMatchQuery)
                .withHighlightFields(new HighlightBuilder.Field("title").preTags("<mark>").postTags("</mark>"),
                        new HighlightBuilder.Field("description").preTags("<mark>").postTags("</mark>"),
                        new HighlightBuilder.Field("content").preTags("<mark>").postTags("</mark>"))
                .build();
        return elasticsearchOperations.search(searchQuery, EsPostEntity.class).getSearchHits();
    }
}
