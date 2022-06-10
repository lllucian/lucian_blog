package com.lucian.common.bean.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author denglingxiang
 * @date 2022/06/02 08:56
 **/
@Data
@TableName("user_roles")
@EqualsAndHashCode(callSuper = true)
public class UserRole extends BaseEntity {
    private static final long serialVersionUID = -8744606867621027416L;
    // 用户id
    private String userId;
    // 角色id
    private String roleId;
}
