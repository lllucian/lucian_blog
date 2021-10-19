package com.lucian.lucian_blog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("user_roles")
@Data
public class UserRole extends BaseEntity{
    // id 主键 已在BaseEntity中设置
    // private Integer id;
}
