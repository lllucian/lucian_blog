package com.lucian.back.bean.vo;

import lombok.Data;

/**
 * 标签list
 * @author lingxiangdeng
 */
@Data
public class TagIndexVO {
    /**
     * 主键id
     */
    private Integer id;
    /**
     * 别名
     */
    private String slug;
    /**
     * 标签名
     */
    private String name;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 相关文章
     */
    private int postCount;
    /**
     * 创建时间
     */
    private String createdAt;
    /**
     * 标签描述
     */
    private String description;
}
