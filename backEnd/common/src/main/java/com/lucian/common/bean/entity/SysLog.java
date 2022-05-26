package com.lucian.common.bean.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统日志实体类
 * @author lingxiangdeng
 */
@EqualsAndHashCode(callSuper = true)
@TableName("sys_logs")
@Data
public class SysLog extends BaseEntity {
    /**
     * 用户id
     */
    private String userId;
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
     * ip地址
     */
    private String ip;
    /**
     * 真实地址
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
