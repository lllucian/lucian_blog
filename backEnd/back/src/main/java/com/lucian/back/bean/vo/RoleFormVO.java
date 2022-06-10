package com.lucian.back.bean.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author denglingxiang
 * @date 2022/06/02 14:22
 **/
@Getter
@Setter
public class RoleFormVO {

    private String name;

    private String zhName;

    /**
     * 去除固定的ROLE部分
     * @param name
     */
    public void setName(String name) {
        if (name.startsWith("ROLE_")){
            name = name.substring(5, name.length());
        }
        this.name = name;
    }
}
