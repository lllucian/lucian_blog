package com.lucian.back.bean.vo;

import lombok.Data;

/**
 * 日志list
 * @author lingxiangdeng
 */
@Data
public class SysLogIndexVO {
    /**
     * 用户名
     */
    private String username;
    /**
     * 操作
     */
    private String operation;
    /**
     * 所花时间
     */
    private Integer time;
    /**
     * 方法
     */
    private String method;
    /**
     * 请求参数
     */
    private String requestParams;
    /**
     * 返回状态
     */
    private Long responseStatus;
    /**
     * ip
     */
    private String ip;
    /**
     * ip归属地
     */
    private String address;
    /**
     * 浏览器
     */
    private String browser;
    /**
     * 操作系统
     */
    private String os;
}
