package com.lucian.common.utils;

import cn.hutool.extra.servlet.ServletUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lingxiangdeng
 */
public class IpUtils {
    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = ServletUtil.getClientIP(request);
        // 本地开发，客户端和服务器在同一台机器，获取到是0:0:0:0:0:0:0:1，ip6地址，需要进行转换
        ipAddress = "0:0:0:0:0:0:0:1".equals(ipAddress) ? "127.0.0.1" : ipAddress;
        return ipAddress;
    }
}
