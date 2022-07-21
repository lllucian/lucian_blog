package com.lucian.back.form_parm;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.lucian.back.annotation.Unique;
import com.lucian.common.bean.enums.EnabledStatusEnum;
import com.lucian.common.bean.enums.LockedStatusEnum;
import com.lucian.common.dao.UserDao;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author denglingxiang
 * @date 2022/05/31 20:52
 **/
@Getter
@Setter
@Unique.List(value = {@Unique(baseMapper= UserDao.class, fields = {"username"}), @Unique(baseMapper= UserDao.class, fields = {"email"})})
public class UserEditParam {

    @TableId("id")
    private String id;
    /**
     * 用户名
     */
    @NotBlank
    @Size(min = 4, message = "最小长度为4")
    @TableField(value = "username")
    private String username;

    /**
     * 密码
     */
    @Size(min = 5, message = "最小长度为5")
    private String password;

    /**
     * 电子邮箱
     */
    @Email(regexp = ".+@.+\\..+")
    @TableField(value = "email")
    private String email;

    /**
     * 是否启用
     */
    private EnabledStatusEnum enabled = EnabledStatusEnum.ENABLED;

    /**
     * 是否被锁定
     */
    private LockedStatusEnum locked = LockedStatusEnum.UNLOCKED;

    /**
     * 角色id
     */
    private List<String> roleIds;
}
