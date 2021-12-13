package org.simpleframework.inject;

import lombok.extern.slf4j.Slf4j;
import org.simpleframework.core.BeanContainer;
import org.simpleframework.inject.annotation.Autowired;
import org.simpleframework.until.ClassUtil;
import org.simpleframework.until.ValidationUtil;

import java.lang.reflect.Field;
import java.util.Set;

/**
 * 依赖注入实现
 *
 * @author bhh
 */

@Slf4j
public class DependencyInjector {
    private BeanContainer beanContainer;

    public DependencyInjector() {
        beanContainer = BeanContainer.INSTANCE;
    }
    /**
     * 执行IOC
     */
    public void doIoc() throws ClassNotFoundException {
//    1.查找全部类
        Set<Class<?>> classSet = beanContainer.getClasses();
        if (ValidationUtil.isEmpty(classSet)) {
            log.warn("Exception in doIoc() : classSet为空");
            return;
        }
//    2.遍历获取类上的属性
        for (Class<?> clazz : classSet) {
            Field[] fields = clazz.getDeclaredFields();
            if (ValidationUtil.isEmpty(fields)) {
                continue;
            }
            for (Field field : fields) {
//    3.查找被Autowired标记的属性
                if (field.isAnnotationPresent(Autowired.class)) {
                    Autowired autowired = field.getAnnotation(Autowired.class);
                    String autowiredValue = autowired.value();
//    4.获取被标记属性的类型
                    Class<?> type = field.getType();
//    5.获取容器中对应的实例
                    Object fieldValue = getFieldInstance(type, autowiredValue);
                    if (fieldValue == null) {
                        throw new RuntimeException("Exception in doIoc() : 遍历field : " + field + "时，fieldValue获取为空");
                    }
//    6.对容器中的属性值进行赋值
                    ClassUtil.setField(field, beanContainer.getBean(clazz), fieldValue, true);
                }
            }
        }
    }

    private Object getFieldInstance(Class<?> fieldClass, String autowiredValue) throws ClassNotFoundException {
        Object instance = beanContainer.getBean(fieldClass);
        //有实例，直接返回
        if (instance != null) {
            return instance;
        }
        //无，查找子类
        return getFieldSubClass(fieldClass, autowiredValue);
    }

    private Object getFieldSubClass(Class<?> fieldClass, String autowiredValue) throws ClassNotFoundException {
        Set<Class<?>> classSet = beanContainer.getClassBySuper(fieldClass);
        if (ValidationUtil.isEmpty(classSet)) {
            return null;
        }
        if (!ValidationUtil.isEmpty(autowiredValue)) {
            Object bean = beanContainer.getBean(Class.forName(autowiredValue));
            if (bean == null) {
                return null;
            } else {
                return bean;
            }
        }
        if (classSet.size() == 1) {
            return beanContainer.getBean(classSet.iterator().next());
        } else {
            log.warn("Exception in getFieldSubClass : 有多个子实例");
            return null;
        }
    }
}

