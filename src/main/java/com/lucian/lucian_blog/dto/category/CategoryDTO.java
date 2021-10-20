package com.lucian.lucian_blog.dto.category;

import lombok.Data;

@Data
public class CategoryDTO {
    private Integer id;

    private String name;

    private String slug;

    private String description;

    private Integer dataNum;
}
