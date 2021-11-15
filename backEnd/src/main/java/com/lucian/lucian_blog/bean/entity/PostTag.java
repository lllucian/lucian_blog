package com.lucian.lucian_blog.bean.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("post_tags")
public class PostTag extends BaseEntity {
    private static final long serialVersionUID = -2644995631199952193L;

    private Integer postId;

    private Integer tagId;
}
