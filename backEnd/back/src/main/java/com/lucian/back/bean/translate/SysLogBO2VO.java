package com.lucian.back.bean.translate;

import com.lucian.back.bean.vo.SysLogIndexVO;
import com.lucian.common.bean.bo.SysLogBO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author lingxiangdeng
 */
@Mapper(componentModel = "spring")
public interface SysLogBO2VO {
    /**
     * 日志bo 转 日志vo
     * @param sysLogBO 日志bo
     * @return 日志vo
     */
    @Mapping(source = "user.username", target = "username")
    SysLogIndexVO translate(SysLogBO sysLogBO);
}
