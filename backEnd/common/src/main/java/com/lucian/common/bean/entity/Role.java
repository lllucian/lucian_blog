package com.lucian.common.bean.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色实体类
 * @author lingxiangdeng
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseEntity {
    /**
     * 序列化id
     */
    private static final long serialVersionUID = -2140179243671989086L;

    /**
     * 角色名（不能包含中文和特殊符号）
     */
    private String name;

    /**
     * 角色中文名
     */
    private String zhName;
}
