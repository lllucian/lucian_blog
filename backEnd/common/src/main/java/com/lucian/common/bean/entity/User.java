package com.lucian.common.bean.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lucian.common.bean.enums.EnabledStatusEnum;
import com.lucian.common.bean.enums.LockedStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户实体类
 * @author lingxiangdeng
 */
@TableName("users")
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {
    /**
     * 序列化id
     */
    private static final long serialVersionUID = 8025629956611743702L;
    /**
     * 昵称
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 上次登陆的ip
     */
    private String signLastIp;
    /**
     * 本次登陆的ip
     */
    private String signCurrentIp;
    /**
     * 是否启用
     */
    private EnabledStatusEnum enabled;
    /**
     * 是否锁定
     */
    private LockedStatusEnum locked;
}
