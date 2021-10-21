package com.lucian.lucian_blog.bean;

public enum LockedStatusEnum {
    LOCKED(true, "锁定"),
    UNLOCKED(false, "未锁定");

    private Boolean status;

    private String label;

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
