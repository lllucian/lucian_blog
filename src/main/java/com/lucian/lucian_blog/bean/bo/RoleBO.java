package com.lucian.lucian_blog.bean.bo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class RoleBO {
    private Integer id;

    private String name;

    private String zhName;

    private List<UserBO> users;

    private Date createdAt;

    private Date updatedAt;

    private Date deletedAt;
}
