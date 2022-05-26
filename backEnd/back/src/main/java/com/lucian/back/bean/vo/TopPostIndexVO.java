package com.lucian.back.bean.vo;

import lombok.Data;

/**
 * @author lingxiangdeng
 */
@Data
public class TopPostIndexVO {
    /**
     * 主键
     */
    private String id;

    /**
     * 文章id
     */
    private String postId;

    /**
     * 文章标题
     */
    private String postTitle;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 是否隐藏
     */
    private Boolean isHidden;
}
