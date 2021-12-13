package org.simpleframework.aop2;

import lombok.Getter;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.simpleframework.aop2.aspect.AspectInfo;
import org.simpleframework.until.ValidationUtil;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AspectListExecutor implements MethodInterceptor {
    //被代理类
    private Class<?> targetClass;
    @Getter
    //aspect集合
    private List<AspectInfo> sortedAspectInfoList;

    public AspectListExecutor(Class<?> targetClass, List<AspectInfo> aspectInfoList) {
        this.targetClass = targetClass;
        //aspectInfoList已经被排序，按照orders由小到大（升序）
        this.sortedAspectInfoList = sortAspectByOrder(aspectInfoList);
    }

    /**
     * 对apAspectInfoList（aspect方法集合）进行排序
     * 排序原因：多个aspect的情况的下，运行顺序需要定义
     *
     * @param aspectInfoList aspect方法的集合
     * @return
     */
    private List<AspectInfo> sortAspectByOrder(List<AspectInfo> aspectInfoList) {
        Collections.sort(aspectInfoList, new Comparator<AspectInfo>() {
            @Override
            public int compare(AspectInfo o1, AspectInfo o2) {
                return o1.getOrderInfo() - o2.getOrderInfo();
            }
        });
        return aspectInfoList;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        Object returnValue = null;
        if (ValidationUtil.isEmpty(sortedAspectInfoList)) {
            return null;
        }
//        1.按照order值升序执行完所有aspect的before方法
        invokeBeforeAdvice(method, args);
        try {
//        2.执行代理类的方法
            returnValue = methodProxy.invokeSuper(obj, args);
//        3.若代理类正常返回，则按照降序顺序完成所有aspect中的afterReturning()方法
            returnValue = invokeAfterReturningAdvice(method, args, returnValue);
        } catch (Exception e) {
//        4.若代理类抛出异常，则按照降序顺序完成所有aspect中的afterThrowing()方法
            invokeAfterThrowingAdvice(method, args, e);
        }
        return returnValue;
    }

    /**
     * 1.按照order值升序执行完所有aspect的before方法
     */
    private void invokeBeforeAdvice(Method method, Object[] args) throws Throwable {
        for (AspectInfo aspectInfo : sortedAspectInfoList) {
            aspectInfo.getAspectObj().before(targetClass, method, args);
        }
    }

    /**
     * 3.若代理类正常返回，则按照降序顺序完成所有aspect中的afterReturning()方法
     */
    private Object invokeAfterReturningAdvice(Method method, Object[] args, Object returnValue) throws Throwable {
        Object obj = null;
        for (int i = sortedAspectInfoList.size() - 1; i >= 0; i--) {
            obj = sortedAspectInfoList.get(i).getAspectObj().afterReturning(targetClass, method, args, returnValue);
        }
        return obj;
    }

    /**
     * 4.若代理类抛出异常，则按照降序顺序完成所有aspect中的afterThrowing()方法
     */
    private void invokeAfterThrowingAdvice(Method method, Object[] args, Exception e) throws Throwable {
        for (int i = sortedAspectInfoList.size() - 1; i >= 0; i--) {
            sortedAspectInfoList.get(i).getAspectObj().afterThrowing(targetClass, method, args, e);
        }
    }
}
