package com.lucian.back.bean.vo;

import lombok.Data;

/**
 * 分类form表单
 * @author lingxiangdeng
 */
@Data
public class CategoryFormVO {
    /**
     * 名称
     */
    private String name;
    /**
     * 别名
     */
    private String slug;
    /**
     * 父分类
     */
    private Integer parentId;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 描述
     */
    private String description;
}
