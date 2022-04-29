package com.lucian.common.bean.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 文章实体类
 * @author lingxiangdeng
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("posts")
public class Post extends BaseEntity {
    /**
     * 序列化id
     */
    private static final long serialVersionUID = 879616852919608771L;
    /**
     *  大标题
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
     * 浏览数
     */
    private Integer viewCount;
    /**
     * 点赞数
     */
    private Integer likes;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 作者id
     */
    private Integer userId;
    /**
     * 封面id
     */
    private Integer uploadFileId;
}
