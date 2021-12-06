package com.lucian.lucian_blog.form_parm;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class TagParam {
    @NotBlank
    @Size(max = 255)
    private String name;

    @NotBlank
    @Size(max = 255)
    private String slug;

    @Range(min = 1, max = 99999)
    private Integer sort;

    private String description;
}
