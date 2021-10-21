package com.lucian.lucian_blog.bean.dto;

import lombok.Data;

import java.util.List;

@Data
public class RoleDTO extends BaseDTO {

    private String Name;

    private String zhName;

    private List<UserDTO> users;
}
