package com.lucian.lucian_blog.bean.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("post_categories")
public class PostCategory extends BaseEntity{
    private static final long serialVersionUID = -605814362323378598L;

    private Integer postId;

    private Integer categoryId;
}
