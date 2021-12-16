package com.lucian.lucian_blog.aspect;

import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucian.lucian_blog.annotation.Log;
import com.lucian.lucian_blog.bean.entity.SysLog;
import com.lucian.lucian_blog.common.CommonResult;
import com.lucian.lucian_blog.dao.SysLogDao;
import com.lucian.lucian_blog.utils.HttpContextUtils;
import com.lucian.lucian_blog.utils.IPUtils;
import com.lucian.lucian_blog.utils.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

@Aspect
@Component
public class LogAspect {

    @Autowired
    SysLogDao sysLogDao;

    @Pointcut("@annotation(com.lucian.lucian_blog.annotation.Log)")
    public void pointcut(){}

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) {
        Object result = null;
        long beginTime = System.currentTimeMillis();
        try {
            // 执行方法
            result = point.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        // 保存日志
        saveLog(point, time, result);
        return result;
    }

    private void saveLog(ProceedingJoinPoint joinPoint, long time, Object resultObj) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SysLog sysLog = new SysLog();
        Log logAnnotation = method.getAnnotation(Log.class);
        if (logAnnotation != null) {
            // 注解上的描述
            sysLog.setOperation(logAnnotation.title());
        }
        // 请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");
        // 请求的方法参数值
        List<Object> objectList = Arrays.stream(joinPoint.getArgs()).filter(item -> Objects.nonNull(item) && !isFilterObject(item)).collect(Collectors.toList());
        sysLog.setRequestParams(JSONUtil.toJsonStr(objectList));
        sysLog.setResponseStatus(((CommonResult)resultObj).getCode());
        // 获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        // 设置IP地址
        sysLog.setIp(IPUtils.getIpAddr(request));
        // 记录当前操作用户Id
        sysLog.setUserId(SecurityUtils.getCurrentUserId());
        UserAgent userAgent = UserAgentUtil.parse(request.getHeader("User-Agent"));
        sysLog.setBrowser(userAgent.getBrowser().getName());
        sysLog.setOs(userAgent.getOs().getName());
        sysLog.setTime((int) time);
        sysLog.setCreatedAt(new Date());
        // 保存系统日志
        sysLogDao.insert(sysLog);
    }

    /**
     * 判断是否需要过滤的对象。
     *
     * @param o 对象信息。
     * @return 如果是需要过滤的对象，则返回true；否则返回false。
     */
    public boolean isFilterObject(final Object o) {
        Class<?> clazz = o.getClass();
        if (clazz.isArray()) {
            return clazz.getComponentType().isAssignableFrom(MultipartFile.class);
        } else if (Collection.class.isAssignableFrom(clazz)) {
            Collection collection = (Collection) o;
            for (Object value : collection) {
                return value instanceof MultipartFile;
            }
        } else if (Map.class.isAssignableFrom(clazz)) {
            Map map = (Map) o;
            for (Object value : map.entrySet()) {
                Map.Entry entry = (Map.Entry) value;
                return entry.getValue() instanceof MultipartFile;
            }
        }
        return o instanceof MultipartFile || o instanceof HttpServletRequest || o instanceof HttpServletResponse
                || o instanceof BindingResult || o instanceof Page;
    }
}
