package com.lucian.lucian_blog.dto.post;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PostDTO {
    private Integer id;

    private String title;

    private String description;

    private String content;

    private UserDTO user;

    private Integer likes;

    private Integer viewCount;

    private List<TagDTO> tags;

    private List<CategoryDTO> categories;

    private Date createdAt;

    private Date updatedAt;

    private Date deletedAt;
}
