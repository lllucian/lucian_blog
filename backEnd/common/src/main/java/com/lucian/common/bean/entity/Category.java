package com.lucian.common.bean.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 分类表
 * @author lingxiangdeng
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("categories")
public class Category extends BaseEntity {

    /**
     * 序列化id
     */
    private static final long serialVersionUID = 2595420458030447858L;

    /**
     * 分类名
     */
    private String name;
    /**
     * 链接上显示的分类名
     */
    private String slug;
    /**
     * 父分类id
     */
    private String parentId;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 描述
     */
    private String description;
}
