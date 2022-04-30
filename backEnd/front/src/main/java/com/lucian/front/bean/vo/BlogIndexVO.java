package com.lucian.front.bean.vo;

import lombok.Data;

/**
 * @author lingxiangdeng
 */
@Data
public class BlogIndexVO {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 封面图片url
     */
    private String imageUrl;

    /**
     * 标题
     */
    private String title;

    /**
     * 描述
     */
    private String description;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 创建时间
     */
    private String createdAt;
}
