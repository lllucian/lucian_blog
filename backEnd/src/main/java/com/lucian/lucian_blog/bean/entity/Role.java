package com.lucian.lucian_blog.bean.entity;

import lombok.Data;

@Data
public class Role extends BaseEntity {
    private static final long serialVersionUID = -2140179243671989086L;

    private String name;

    private String zhName;
}
