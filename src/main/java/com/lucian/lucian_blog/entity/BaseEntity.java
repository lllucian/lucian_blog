package com.lucian.lucian_blog.entity;

import java.util.Date;

/**
 * 通用实体类
 */
public class BaseEntity {
    // 主键
    private Integer id;
    // 创建时间
    private Date createdAt;
    // 修改时间
    private Date updatedAt;
    // 删除时间
    private Date deletedAt;
}
