package com.lucian.back.form_parm;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author lingxiangdeng
 */
@Data
public class CategoryParam {
    /**
     * 分类名
     */
    @NotBlank
    @Size(max = 255)
    private String name;
    /**
     * 别名
     */
    @NotBlank
    @Size(max = 255)
    private String slug;
    /**
     * 父分类id
     */
    private Integer parentId;
    /**
     * 排序
     */
    @Range(min = 1, max = 99999)
    private Integer sort;
    /**
     * 描述
     */
    private String description;
}
