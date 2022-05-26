package com.lucian.back.bean.vo;

import lombok.Data;

import java.util.List;

/**
 * 文章list
 * @author lingxiangdeng
 */
@Data
public class PostIndexVO {
    /**
     * 文章id
     */
    private String id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章描述
     */
    private String description;

    /**
     * 分类
     */
    private List<String> categories;

    /**
     * 标签
     */
    private List<String> tags;

    /**
     * 创建时间
     */
    private String createdAt;

    /**
     * 更新时间
     */
    private String updatedAt;
}
