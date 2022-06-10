package com.lucian.back.form_parm;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @author denglingxiang
 * @date 2022/06/02 14:01
 **/
@Getter
@Setter
public class RoleParam {
    // 权限名
    @NotBlank
    private String name;
    // 权限中文名
    @NotBlank
    private String zhName;
}
