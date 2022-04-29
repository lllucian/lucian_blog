package com.lucian.back.bean.translate;

import com.lucian.common.bean.bo.SysLogBO;
import com.lucian.common.bean.entity.SysLog;
import com.lucian.common.bean.entity.User;
import com.lucian.back.dao.UserDao;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author lingxiangdeng
 */
@Mapper(componentModel = "spring")
public abstract class SysLog2BO {

    UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * 日志对象转换
     * @param sysLogs 系统日志
     * @return 系统日志 bo形态
     */
    @Mapping(target = "username", ignore = true)
    public abstract List<SysLogBO> translateList(List<SysLog> sysLogs);

    /**
     * 转换user信息
     * @param sysLogs 系统日志
     * @param sysLogBOS 系统日志bo形态
     */
    @AfterMapping
    protected void setUserInfo(List<SysLog> sysLogs, @MappingTarget List<SysLogBO> sysLogBOS){
        if (sysLogs == null) {
            return;
        }
        // 得出所有log里的userId
        List<Integer> userIds =sysLogs.stream().map(SysLog::getUserId).distinct().collect(Collectors.toList());
        if (userIds.size() == 0) {
            return;
        }
        List<User> users = userDao.selectBatchIds(userIds);
        Map<Integer, User> userMap = users.stream().collect(Collectors.toMap(User::getId, user -> user, (oldValue, newValue) -> oldValue));
        // 得出log和userId的映射map
        Map<Integer, Integer> logUserRelations = sysLogs.stream().collect(Collectors.toMap(SysLog::getId, SysLog::getUserId, (oldValue, newValue) -> oldValue));
        sysLogBOS.forEach(sysLogBO -> {
            if (logUserRelations.containsKey(sysLogBO.getId())) {
                sysLogBO.setUser(userMap.get(logUserRelations.get(sysLogBO.getId())));
            }
        });
    }

}
