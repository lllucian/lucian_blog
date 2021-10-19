package com.lucian.lucian_blog.entity;

import java.util.Date;

/**
 * 通用实体类
 */
public class BaseEntity {
    // 主键
    private Integer id;
    // 创建时间
    private Date createdTime;
    // 修改时间
    private Date updatedTime;
    // 删除时间
    private Date deletedTime;
}
