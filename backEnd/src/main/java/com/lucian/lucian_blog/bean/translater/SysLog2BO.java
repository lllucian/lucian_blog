package com.lucian.lucian_blog.bean.translater;

import com.lucian.lucian_blog.bean.bo.SysLogBO;
import com.lucian.lucian_blog.bean.entity.SysLog;
import com.lucian.lucian_blog.bean.entity.User;
import com.lucian.lucian_blog.dao.UserDao;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class SysLog2BO {

    UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Mapping(target = "username", ignore = true)
    public abstract List<SysLogBO> translateList(List<SysLog> sysLogs);

    @AfterMapping
    protected void setUserInfo(List<SysLog> sysLogs, @MappingTarget List<SysLogBO> sysLogBOS){
        if (sysLogs == null) return;
        // 得出所有log里的userId
        List<Integer> userIds =sysLogs.stream().map(SysLog::getUserId).distinct().collect(Collectors.toList());
        if (userIds.size() == 0) return;
        List<User> users = userDao.selectBatchIds(userIds);
        Map<Integer, User> userMap = users.stream().collect(Collectors.toMap(User::getId, user -> user, (oldValue, newValue) -> oldValue));
        // 得出log和userId的映射map
        Map<Integer, Integer> logUserRelations = sysLogs.stream().collect(Collectors.toMap(SysLog::getId, SysLog::getUserId, (oldValue, newValue) -> oldValue));
        sysLogBOS.forEach(sysLogBO -> {
            if (logUserRelations.containsKey(sysLogBO.getId())) sysLogBO.setUser(userMap.get(logUserRelations.get(sysLogBO.getId())));
        });
    }

}
