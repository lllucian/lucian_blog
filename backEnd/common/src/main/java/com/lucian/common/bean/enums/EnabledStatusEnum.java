package com.lucian.common.bean.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * @author lingxiangdeng
 */
public enum EnabledStatusEnum {
    /**
     * 账号启用状态为true
     * 账号禁用状态为false
     */
    ENABLED(true, "启用"),
    DISABLED(false, "未启用");

    @EnumValue
    private final Boolean status;

    private final String label;

    EnabledStatusEnum(Boolean status, String label){
        this.status = status;
        this.label = label;
    }

    /**
     * 获取数据库中字段的信息
     * @return true or false
     */
    public Boolean getStatus(){
        return status;
    }

    public String getLabel(){
        return label;
    }
}
