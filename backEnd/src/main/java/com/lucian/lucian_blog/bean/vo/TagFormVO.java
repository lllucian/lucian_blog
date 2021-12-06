package com.lucian.lucian_blog.bean.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TagFormVO {
    private String name;
    
    private String slug;
    
    private String description;
    
    private Integer sort;
}
