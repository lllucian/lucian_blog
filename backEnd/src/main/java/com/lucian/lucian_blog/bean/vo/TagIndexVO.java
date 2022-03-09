package com.lucian.lucian_blog.bean.vo;

import lombok.Data;

@Data
public class TagIndexVO {
    //主键
    private Integer id;
    //别名
    private String slug;
    //标签名
    private String name;
    //排序
    private Integer sort;
    //相关文章数量
    private int postCount;
    //创建时间
    private String createdAt;
    //标签描述
    private String description;
}
