package com.lucian.back.form_parm;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author lingxiangdeng
 */
@Getter
@Setter
public class TagParam {
    /**
     * 标签名
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
     * 排序
     */
    @Range(min = 1, max = 99999)
    private Integer sort;

    /**
     * 描述
     */
    private String description;
}
