package com.lucian.common.bean.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 文章标签关联实体类
 * @author lingxiangdeng
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("post_tags")
public class PostTag extends BaseEntity {
    /**
     * 序列化id
     */
    private static final long serialVersionUID = -2644995631199952193L;
    /**
     * 文章id
     */
    private String postId;
    /**
     * 标签id
     */
    private String tagId;
}
