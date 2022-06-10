package com.lucian.back.form_parm;

import com.lucian.common.bean.enums.EnabledStatusEnum;
import com.lucian.common.bean.enums.LockedStatusEnum;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author denglingxiang
 * @date 2022/05/31 20:52
 **/
@Getter
@Setter
public class UserParam {
    /**
     * 用户名
     */
    @NotBlank
    @Size(min = 4, message = "最小长度为4")
    private String username;

    /**
     * 密码
     */
    @NotBlank
    @Size(min = 6, message = "最小长度为6")
    private String password;

    /**
     * 电子邮箱
     */
    @Email(regexp = ".+@.+\\..+")
    private String email;

    /**
     * 是否启用
     */
    private EnabledStatusEnum enabled;

    /**
     * 是否被锁定
     */
    private LockedStatusEnum locked;
}
