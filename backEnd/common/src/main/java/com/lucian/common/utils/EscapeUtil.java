package com.lucian.common.utils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;

/**
 * mybaits 字符转义
 * @author lingxiangdeng
 */
public class EscapeUtil {
    /**
     * mysql的模糊查询时特殊字符转义
     * @param before 替换前的语句
     * @return 替换特殊字符后的语句
     */
    public static String escapeChar(String before){
        if(StringUtils.isNotBlank(before)){
            before = before.replaceAll("\\\\", "\\\\\\\\");
            before = before.replaceAll("_", "\\\\_");
            before = before.replaceAll("%", "\\\\%");
        }
        return before ;
    }
}
