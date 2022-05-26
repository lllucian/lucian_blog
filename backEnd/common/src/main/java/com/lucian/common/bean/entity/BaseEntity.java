package com.lucian.common.bean.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

/**
 * 通用实体类
 * @author lingxiangdeng
 */
@Data
public class BaseEntity implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    @Id
    private String id;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @Field(type = FieldType.Date, format = DateFormat.basic_date_time_no_millis)
    private Date createdAt;
    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Field(type = FieldType.Date, format = DateFormat.basic_date_time_no_millis)
    private Date updatedAt;
    /**
     * 删除时间
     */
    @Field(type = FieldType.Date, format = DateFormat.basic_date_time_no_millis)
    private Date deletedAt;
}
