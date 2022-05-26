package com.lucian.back.form_parm;

import lombok.Data;

/**
 * @author lingxiangdeng
 */
@Data
public class TopPostParam {
    private String postId;

    private Boolean isHidden;

    private Integer sort;
}
