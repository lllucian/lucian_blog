package com.lucian.common.bean.es_entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.io.Serializable;

/**
 * @author denglingxiang
 * @date 2022/05/28 18:00
 **/
@Data
@Document(indexName = "posts")
@Setting(settingPath = "ik_max_word_html_strip.json")
public class EsPostEntity implements Serializable {

    private static final long serialVersionUID = 2004079376089944400L;

    @Id
    private String id;

    @Field(type = FieldType.Keyword)
    private String title;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String description;

    @Field(type = FieldType.Text, analyzer = "ik_max_word_html_strip")
    private String content;
}
