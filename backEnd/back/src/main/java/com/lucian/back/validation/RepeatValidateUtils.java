package com.lucian.back.validation;

import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Component;

import javax.validation.ValidationException;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @author denglingxiang
 * @date 2022/06/13 09:10
 **/
@Component
public class RepeatValidateUtils {
    /**
     * 实体类中id字段
     */
    private String idColumnName;

    /**
     * 实体类中id的值
     */
    private Object idColumnValue;

    /**
     * @param fields  验证的字段数组
     * @param message 如果不满足返回的消息
     * @param o       实体类
     * @return 是否重复
     */
    public boolean fieldRepeat(String[] fields, String message, Object o, Class<? extends BaseMapper<?>> baseMapper) throws ValidationException, IllegalAccessException, InstantiationException {
        try {
            // 没有校验的值返回true
            if (fields != null && fields.length == 0) {
                return true;
            }
            checkUpdateOrSave(o);
            checkRepeat(fields, o, message, baseMapper);
        } catch (ValidationException ed) {
            return  false;
        } catch (IllegalAccessException e) {
            throw new IllegalAccessException(e.getMessage());
        }
        return true;
    }

    /**
     * 通过传入的实体类中 @TableId 注解的值是否为空，来判断是更新还是保存
     * 将值id值和id列名赋值
     * id的值不为空 是更新 否则是插入
     *
     * @param o 被注解修饰过的实体类
     */
    public void checkUpdateOrSave(Object o) throws IllegalAccessException {
        Field[] fields = getAllFields(o.getClass());
        for (Field f : fields) {
            // 设置私有属性可读
            f.setAccessible(true);
            if (f.isAnnotationPresent(TableId.class)) {
                TableId tableId = f.getAnnotation(TableId.class);
                idColumnName = tableId.value();
                idColumnValue = f.get(o);
            }
        }
    }

    /**
     * 获取本类及其父类的属性的方法
     *
     * @param clazz 当前类对象
     * @return 字段数组
     */
    private static Field[] getAllFields(Class<?> clazz) {
        List<Field> fieldList = new ArrayList<>();
        while (clazz != null) {
            fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
            clazz = clazz.getSuperclass();
        }
        Field[] fields = new Field[fieldList.size()];
        return fieldList.toArray(fields);
    }


    /**
     * 通过传入的字段值获取数据是否重复
     *
     * @param fields  要检查的字段
     * @param o       实体类
     * @param message 错误信息
     */
    public void checkRepeat(String[] fields, Object o, String message, Class<? extends BaseMapper<?>> baseMapper) throws ValidationException, IllegalAccessException, InstantiationException {
        BaseMapper<Object> bean = (BaseMapper<Object>) SpringUtil.getBean(baseMapper);
        //Mybatis-plus 3.0以下用EntityWrapper
        QueryWrapper<Object> qw = new QueryWrapper<>();
        Map<String, Object> queryMap = getColumns(fields, o);
        for (Map.Entry<String, Object> entry : queryMap.entrySet()) {
            qw.eq(entry.getKey(), entry.getValue());
        }
        if (idColumnValue != null) {
            //更新的话，那条件就要排除自身
            qw.ne(idColumnName, idColumnValue);
        }


        List<?> list = bean.selectList(qw);
        if (list != null && list.size() > 0) {
            throw new ValidationException(message);
        }
    }


    /**
     * 多条件判断唯一性，将我们的属性和值组装在map中，方便后续拼接条件
     *
     * @param fields 要验证的属性
     * @param o      实体类对象
     * @return 字段信息
     */
    public Map<String, Object> getColumns(String[] fields, Object o) throws IllegalAccessException {
        Field[] fieldList = getAllFields(o.getClass());
        Map<String, Object> map = new HashMap<>();
        for (Field f : fieldList) {
            // ② 设置对象中成员 属性private为可读
            f.setAccessible(true);
            // 判断字段是否包含在数组中，如果存在，则将它对应的列字段放入map中
            if (ArrayUtils.contains(fields, f.getName())) {
                getMapData(map, f, o);
            }
        }
        return map;
    }

    /**
     * 得到查询条件
     *
     * @param map 列字段
     * @param f   字段
     * @param o   传入的对象
     */
    private void getMapData(Map<String, Object> map, Field f, Object o) throws IllegalAccessException {
        try {
            if (f.isAnnotationPresent(TableField.class)) {
                TableField tableField = f.getAnnotation(TableField.class);
                Object val = f.get(o);
                map.put(tableField.value(), val);
            }
        } catch (IllegalAccessException i) {
            throw new IllegalAccessException("获取字段的值错误");
        }
    }
}
