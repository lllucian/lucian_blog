package com.lucian.back.bean.vo;

import lombok.Data;

/**
 * 标签表单
 * @author lingxiangdeng
 */
@Data
public class TagFormVO {
    /**
     * 标签名
     */
    private String name;
    /**
     * 别名
     */
    private String slug;
    /**
     * 描述
     */
    private String description;
    /**
     * 排序
     */
    private Integer sort;
}
