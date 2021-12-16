package com.lucian.lucian_blog.bean.bo;

import com.lucian.lucian_blog.bean.entity.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SysLogBO {
    private Integer id;
    // 操作者
    private User user;
    // 操作
    private String operation;
    // 执行时间
    private Integer time;
    // 执行方法
    private String method;
    // 请求参数
    private String requestParams;
    // 返回参数
    private Long responseStatus;
    // 请求ip
    private String ip;
    // 请求地址
    private String address;
    // 浏览器类型
    private String browser;
    // 系统类型
    private String os;
}
