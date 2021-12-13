package org.simpleframework.aop1;

import org.simpleframework.aop1.annotation.Aspect;
import org.simpleframework.aop1.annotation.Order;
import org.simpleframework.aop1.aspect.AspectInfo;
import org.simpleframework.aop1.aspect.DefaultAspect;
import org.simpleframework.core.BeanContainer;
import org.simpleframework.until.ValidationUtil;

import java.lang.annotation.Annotation;
import java.util.*;

public class AspectWeaver {
    private BeanContainer beanContainer;

    public AspectWeaver() {
        this.beanContainer = BeanContainer.INSTANCE;
    }

    public void doAop() {
//        1.获取所有的切面类
        Set<Class<?>> aspectSet = beanContainer.getClassByAnnotation(Aspect.class);
        if (ValidationUtil.isEmpty(aspectSet)) {
            return;
        }
//        2.将切面类按照不同的切入目标进行切分
        Map<Class<? extends Annotation>, List<AspectInfo>> categorizedMap = new HashMap();
        for (Class<?> aspectClass : aspectSet) {
            if (verifyAspect(aspectClass)) {
                categorizeAspect(categorizedMap, aspectClass);
            } else {
                throw new RuntimeException("Exception in doAop: verifyAspect(aspectClass)未通过");
            }
        }
//        3.按照不同的织入目标分别去按序织入aspect逻辑
        if (ValidationUtil.isEmpty(categorizedMap)) {
            return;
        }
        for (Class<? extends Annotation> category : categorizedMap.keySet()) {
            weaveByCategory(category, categorizedMap.get(category));
        }
    }

    /**
     * 按照不同的织入目标分别去按序织入aspect逻辑
     *
     * @param category    某一个切面类中的value
     * @param aspectInfos categorizedMap中对应切面类的信息
     */
    private void weaveByCategory(Class<? extends Annotation> category, List<AspectInfo> aspectInfos) {
        Set<Class<?>> categorySet = beanContainer.getClassByAnnotation(category);
        if (ValidationUtil.isEmpty(categorySet)) {
            return;
        }
        for (Class<?> targetClass : categorySet) {
            AspectListExecutor aspectListExecutor = new AspectListExecutor(targetClass, aspectInfos);
            Object proxyClass = ProxyCreate.createProxy(targetClass, aspectListExecutor);
            beanContainer.addBean(targetClass, proxyClass);
        }
    }

    /**
     * 将切面类按照不同的切入目标进行切分
     *
     * @param categorizedMap 存储 切面类的value类以及切面信息的类 的集合
     * @param aspectClass    切面类
     */
    private void categorizeAspect(Map<Class<? extends Annotation>, List<AspectInfo>> categorizedMap, Class<?> aspectClass) {
        Aspect aspectTag = aspectClass.getAnnotation(Aspect.class);
        Order orderTag = aspectClass.getAnnotation(Order.class);
        DefaultAspect aspect = (DefaultAspect) beanContainer.getBean(aspectClass);
        AspectInfo aspectInfo = new AspectInfo(orderTag.value(), aspect);
        if (!categorizedMap.containsKey(aspectTag.value())) {
            // 切入点第一次被加载
            List<AspectInfo> aspectInfoList = new ArrayList<>();
            aspectInfoList.add(aspectInfo);
            categorizedMap.put(aspectTag.value(), aspectInfoList);
        } else {
            // 切入点已经被加载过
            List<AspectInfo> aspectInfoList = categorizedMap.get(aspectTag.value());
            aspectInfoList.add(aspectInfo);
        }

    }

    /**
     * 对aspect进行验证
     * 1.切面类必须含有注解Aspect
     * 2.切面类必须含有注解Order
     * 3.切面类必须继承自DefaultAspect
     * 4.切面类的注解Aspect的值不可是Aspect
     *
     * @param aspectClass 切面类
     * @return 返回验证结果
     */
    private boolean verifyAspect(Class<?> aspectClass) {
        return aspectClass.isAnnotationPresent(Aspect.class) &&
                aspectClass.isAnnotationPresent(Order.class) &&
                DefaultAspect.class.isAssignableFrom(aspectClass) &&
                aspectClass.getAnnotation(Aspect.class).value() != Aspect.class;
    }
}
