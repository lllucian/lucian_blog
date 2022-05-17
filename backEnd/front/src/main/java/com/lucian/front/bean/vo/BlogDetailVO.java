package com.lucian.front.bean.vo;

import lombok.Data;

/**
 * @author denglingxiang
 * @date 2022/05/16 13:59
 **/
@Data
public class BlogDetailVO {
    /**
     * 封面图片
     */
    private String imageUrl;

    /**
     * 作者用户名
     */
    private String username;

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
     * 创建时间
     */
    private String createdAt;

    /**
     * 更新时间
     */
    private String updatedAt;

}
