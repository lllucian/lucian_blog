package com.lucian.back.bean.vo;

import lombok.Data;

/**
 * 分类list
 * @author lingxiangdeng
 */
@Data
public class CategoryIndexVO {
    /**
     * 分类id
     */
    private Integer id;
    /**
     * 分类名
     */
    private String name;

    /**
     * 别名
     */
    private String slug;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 被关联的文章数量
     */
    private Integer postCount;

    /**
     * 创建时间
     */
    private String createdAt;

    /**
     * 父分类
     */
    private String parentName;

    /**
     * 描述
     */
    private String description;
}
