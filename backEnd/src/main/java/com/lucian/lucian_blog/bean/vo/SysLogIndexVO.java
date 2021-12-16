package com.lucian.lucian_blog.bean.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysLogIndexVO {
    private String username;
    private String operation;
    private Integer time;
    private String method;
    private String requestParams;
    private Long responseStatus;
    private String ip;
    private String address;
    private String browser;
    private String os;
}
