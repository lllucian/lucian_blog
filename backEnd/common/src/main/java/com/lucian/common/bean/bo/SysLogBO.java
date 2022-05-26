package com.lucian.common.bean.bo;

import com.lucian.common.bean.entity.User;
import lombok.Data;

/**
 * @author lingxiangdeng
 */
@Data
public class SysLogBO {
    private String id;
    /**
     * 操作者
     */
    private User user;
    /**
     * 操作
     */
    private String operation;
    /**
     * 执行时间
     */
    private Integer time;
    /**
     * 执行方法
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
     * 请求ip
     */
    private String ip;
    /**
     * 请求地址
     */
    private String address;
    /**
     * 浏览器类型
     */
    private String browser;
    /**
     * 系统信息
     */
    private String os;
}
