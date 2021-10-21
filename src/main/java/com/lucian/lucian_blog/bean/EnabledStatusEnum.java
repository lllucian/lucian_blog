package com.lucian.lucian_blog.bean;

public enum EnabledStatusEnum {
    ENABLED(true, "启用"),
    DISABLED(false, "未启用");

    private Boolean status;

    private String label;

    EnabledStatusEnum(Boolean status, String label){
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
