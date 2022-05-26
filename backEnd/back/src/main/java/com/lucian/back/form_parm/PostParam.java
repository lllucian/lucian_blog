package com.lucian.back.form_parm;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author lingxiangdeng
 */
@Getter
@Setter
@ToString
public class PostParam {

    /**
     * 标题
     */
    @NotBlank
    @Size(max = 255)
    private String title;

    /**
     * 描述
     */
    private String description;

    /**
     * 内容
     */
    @NotBlank
    private String content;

    /**
     * 排序
     */
    @Range(min = 1, max = 99999)
    private Integer sort;

    /**
     * 分类
     */
    private List<String> categories;

    /**
     * 标签
     */
    private List<String> tags;

    /**
     * 封面
     */
    @NotBlank
    private String uploadFileId;
}
