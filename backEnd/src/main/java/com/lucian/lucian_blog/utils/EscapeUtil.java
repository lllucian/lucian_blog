package com.lucian.lucian_blog.utils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;

public class EscapeUtil {
    //mysql的模糊查询时特殊字符转义
    public static String escapeChar(String before){
        if(StringUtils.isNotBlank(before)){
            before = before.replaceAll("\\\\", "\\\\\\\\");
            before = before.replaceAll("_", "\\\\_");
            before = before.replaceAll("%", "\\\\%");
        }
        return before ;
    }
}
