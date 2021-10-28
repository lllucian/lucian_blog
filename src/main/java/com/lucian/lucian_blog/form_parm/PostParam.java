package com.lucian.lucian_blog.form_parm;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@ToString
public class PostParam {

    @NotBlank
    @Size(max = 255)
    private String title;

    private String description;

    @NotBlank
    private String content;

    private List<Integer> categories;

    private List<Integer> tags;
}
