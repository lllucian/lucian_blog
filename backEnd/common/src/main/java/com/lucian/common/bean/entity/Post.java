package com.lucian.common.bean.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.elasticsearch.annotations.*;

/**
 * 文章实体类
 * @author lingxiangdeng
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("posts")
@Document(indexName = "posts")
@Setting(settingPath = "ik_max_word_html_strip.json")
@Mapping
public class Post extends BaseEntity {
    /**
     * 序列化id
     */
    private static final long serialVersionUID = 879616852919608771L;

    /**
     *  大标题
    */
    @Field(type = FieldType.Keyword)
    private String title;
    /**
     * 描述
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word_html_strip")
    private String description;
    /**
     * 内容
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word_html_strip")
    private String content;
    /**
     * 浏览数
     */
    private Integer viewCount;
    /**
     * 点赞数
     */
    private Integer likes;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 作者id
     */
    private String userId;
    /**
     * 封面id
     */
    private String uploadFileId;
}
