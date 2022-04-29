package com.lucian.back.bean.vo;

import lombok.Data;

import java.util.List;

/**
 * 文章表单
 * @author lingxiangdeng
 */
@Data
public class PostFormVO{

    /**
     * id 主键
     */
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 描述
     */
    private String description;

    /**
     * 内容
     */
    private String content;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 更新时间
     */
    private String updatedAt;

    /**
     * 创建时间
     */
    private String createdAt;

    /**
     * 分类
     */
    private List<Integer> categories;

    /**
     * 标签
     */
    private List<Integer> tags;

    /**
     * 封面图片上传至uploadFile的id
     */
    private Integer fileUpload;
}
