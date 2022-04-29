package com.lucian.common.bean.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 标签实体类
 * @author lingxiangdeng
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tags")
public class Tag extends BaseEntity{
    /**
     * 序列化id
     */
    private static final long serialVersionUID = 5815153049744753397L;
    /**
     * 标签名
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
     * 描述
     */
    private String description;
}
