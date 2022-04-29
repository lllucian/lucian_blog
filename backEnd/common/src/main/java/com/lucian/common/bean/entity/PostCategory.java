package com.lucian.common.bean.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 文章分类关联实体类
 * @author lingxiangdeng
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("post_categories")
public class PostCategory extends BaseEntity {
    /**
     * 序列化id
     */
    private static final long serialVersionUID = -605814362323378598L;

    /**
     * 文章id
     */
    private Integer postId;

    /**
     * 分类id
     */
    private Integer categoryId;
}
