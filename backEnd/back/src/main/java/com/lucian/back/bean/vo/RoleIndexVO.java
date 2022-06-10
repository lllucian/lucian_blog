package com.lucian.back.bean.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author denglingxiang
 * @date 2022/06/01 14:38
 **/
@Getter
@Setter
public class RoleIndexVO {
    // 权限id
    private String id;
    //权限名
    private String name;
    //权限中文名
    private String zhName;
    //权限被关联的账号数量
    private Integer accountNumber = 0;
}
