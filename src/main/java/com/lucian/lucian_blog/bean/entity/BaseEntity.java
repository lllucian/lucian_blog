package com.lucian.lucian_blog.bean.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * 通用实体类
 */
@Data
public class BaseEntity implements Serializable {
    // 主键
    @TableId(type = IdType.AUTO)
    private Integer id;
    // 创建时间
    @TableField(fill = FieldFill.INSERT)
    private Date createdAt;
    // 修改时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedAt;
    // 删除时间
    private Date deletedAt;
}
