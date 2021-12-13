package org.simpleframework.core;

import lombok.extern.slf4j.Slf4j;
import org.simpleframework.aop1.annotation.Aspect;
import org.simpleframework.core.annotation.Component;
import org.simpleframework.core.annotation.Controller;
import org.simpleframework.core.annotation.Repository;
import org.simpleframework.core.annotation.Service;
import org.simpleframework.until.ClassUtil;
import org.simpleframework.until.ValidationUtil;

import java.lang.annotation.Annotation;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author bhh
 */
@Slf4j
public enum BeanContainer {
    INSTANCE;

    public BeanContainer getInstance() {
        return INSTANCE;
    }

    /**
     * 判断是否已经加载过Bean
     */
    private Boolean isLoaded = false;

    public Boolean getIsLoaded() {
        return isLoaded;
    }

    /**
     * 存放所有被配置标记的目标对象的Map
     */
    private final Map<Class<?>, Object> beanMap = new ConcurrentHashMap<Class<?>, Object>();

    public Map<Class<?>, Object> getBeanMap() {
        return beanMap;
    }

    /**
     * 加载bean的注解列表
     */
    private static final List<Class<? extends Annotation>> BEAN_ANNOTATION
            = Arrays.asList(Component.class, Controller.class, Service.class, Repository.class, Aspect.class);

    /**
     * 加载所有的Bean
     *
     * @param packageName
     */
    public synchronized void loadBeans(String packageName) {
        if (isLoaded) {
            log.warn("Exception in loadBeans : 容器已经记载过bean");
            return;
        }
        Set<Class<?>> classSet = ClassUtil.extractPackageClass(packageName);
        if (ValidationUtil.isEmpty(classSet)) {
            log.warn("Exception in loadBeans : packageName为空");
            return;
        }
        for (Class<?> clazz : classSet) {
            for (Class<? extends Annotation> annotationClass : BEAN_ANNOTATION) {
                if (clazz.isAnnotationPresent(annotationClass)) {
                    beanMap.put(clazz, ClassUtil.newInstatnce(clazz, true));
                }
            }
        }
        isLoaded = true;
    }

    //容器操作实现：

    /**
     * 实现容器添加bean
     *
     * @param clazz 需要添加的Class对象
     * @param obj   需要添加的实例对象
     * @return 返回obj（详情看put源码）
     */
    public Object addBean(Class<?> clazz, Object obj) {
        return beanMap.put(clazz, obj);
    }

    /**
     * 实现容器移除bean
     *
     * @param clazz
     * @return 返回key对应的obj（详情看remove源码）
     */
    public Object deleteBean(Class<?> clazz) {
        return beanMap.remove(clazz);
    }

    /**
     * 根据class获取对应的实例
     *
     * @param clazz Class对像
     * @return key对应的实例对象（详情看get源码）
     */
    public Object getBean(Class<?> clazz) {
        return beanMap.get(clazz);
    }

    /**
     * 获取所有的class对象集合
     *
     * @return key集合
     */
    public Set<Class<?>> getClasses() {
        return (Set<Class<?>>)beanMap.keySet();
    }

    /**
     * 获取所有的bean实例集合
     *
     * @return value集合
     */
    public Collection<Object> getBeans() {
        return (Collection<Object>) beanMap.values();
    }

    /**
     * 根据注解获取所有对应class集合
     *
     * @param annotation 注解类
     * @return 返回集合
     */
    public Set<Class<?>> getClassByAnnotation(Class<? extends Annotation> annotation) {
//        1.获取全部class
        Set<Class<?>> keySet = getClasses();
        if (ValidationUtil.isEmpty(keySet)) {
            log.warn("Exception in getClassByAnnotation : keySet为空");
            return null;
        }
//        2.依次查找符合条件的class
        Set<Class<?>> classSet = new HashSet<>();
        for (Class<?> clazz : keySet) {
            if (clazz.isAnnotationPresent(annotation)) {
                classSet.add(clazz);
            }
        }
        return classSet.size() > 0 ? classSet : null;
    }

    /**
     * 根据继承关系获取其子类Class
     *
     * @param interfaceOrClass 父类或者接口
     * @return 返回集合
     */
    public Set<Class<?>> getClassBySuper(Class<?> interfaceOrClass) {
//        1.获取全部class
        Set<Class<?>> keySet = getClasses();
        if (ValidationUtil.isEmpty(keySet)) {
            log.warn("Exception in getClassBySuper : keySet为空");
            return null;
        }
//        2.依次查找获取符合条件的class
        Set<Class<?>> classSet = new HashSet<>();
        for (Class<?> clazz : keySet) {
            if (interfaceOrClass.isAssignableFrom(clazz)) {
                classSet.add(clazz);
            }
        }
        return classSet.size() > 0 ? classSet : null;
    }

    /**
     * 获取容器中bean的数量
     *
     * @return 数量
     */
    public int size() {
        return beanMap.size();
    }
}
