package com.lucian.common.bean.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * @author lingxiangdeng
 */

public enum LockedStatusEnum {
    /**
     * 账号锁定状态为true
     * 账号为未锁定状态为false
     */
    LOCKED(true, "锁定"),
    UNLOCKED(false, "未锁定");

    @EnumValue
    private final Boolean status;

    private final String label;

    LockedStatusEnum(Boolean status, String label){
        this.status = status;
        this.label = label;
    }

    public Boolean getStatus(){
        return status;
    }

    public String getLabel(){
        return label;
    }
}
