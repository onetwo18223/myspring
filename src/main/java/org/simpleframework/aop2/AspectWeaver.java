package org.simpleframework.aop2;

import org.simpleframework.aop2.annotation.Aspect;
import org.simpleframework.aop2.aspect.AspectInfo;
import org.simpleframework.core.BeanContainer;
import org.simpleframework.until.ValidationUtil;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
//        2.拼装AspectInfoList
        List<AspectInfo> aspectInfoList = packAspectInfoList(aspectSet);
//        3.遍历容器中的类
        Set<Class<?>> classSet = beanContainer.getClasses();
        if (ValidationUtil.isEmpty(classSet)) {
            return;
        }
        for (Class<?> targetClass : classSet) {
            //排除目标类为aspect类
            if (targetClass.isAnnotationPresent(Aspect.class)) {
                continue;
            }
//        4.粗筛符合条件的aspect
            List<AspectInfo> roughMatchAspectList = collectRoughMatchedAspectListForSpecificClass(aspectInfoList, targetClass);
//        5.尝试进行aspect的织入
            wrapIfNecessary(roughMatchAspectList, targetClass);
        }
    }

    private void wrapIfNecessary(List<AspectInfo> roughMatchAspectList, Class<?> targetClass) {
    }

    private List<AspectInfo> collectRoughMatchedAspectListForSpecificClass(List<AspectInfo> aspectInfoList, Class<?> targetClass) {
        return null;
    }


    private List<AspectInfo> packAspectInfoList(Set<Class<?>> aspectSet) {
        List<AspectInfo> aspectInfoList = new ArrayList<>();
        return null;
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
}
