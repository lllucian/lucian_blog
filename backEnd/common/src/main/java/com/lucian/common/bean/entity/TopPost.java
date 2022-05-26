package com.lucian.common.bean.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lingxiangdeng
 * 前端滚动图实体类
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("top_posts")
public class TopPost extends BaseEntity{
    /**
     * 文章id
     */
    private String postId;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 是否隐藏
     */
    private Boolean isHidden;
}
