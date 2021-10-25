package com.lucian.lucian_blog.bean.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("post_categories")
public class PostCategory extends BaseEntity{
    private static final long serialVersionUID = -605814362323378598L;

    private Integer postId;

    private Integer categoryId;
}
