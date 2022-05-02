package com.lucian.back.form_parm;

import lombok.Data;

/**
 * @author lingxiangdeng
 */
@Data
public class TopPostParam {
    private Integer postId;

    private Boolean isHidden;

    private Integer sort;
}
