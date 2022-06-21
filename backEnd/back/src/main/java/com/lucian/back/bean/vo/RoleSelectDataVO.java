package com.lucian.back.bean.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author denglingxiang
 * @date 2022/06/20 08:45
 **/
@Getter
@Setter
public class RoleSelectDataVO {
    // 主键
    private String value;
    // 标签(中文权限名 + 权限名)
    private String label;
}
