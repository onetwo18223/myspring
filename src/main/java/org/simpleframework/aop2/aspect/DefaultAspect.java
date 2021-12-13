package org.simpleframework.aop2.aspect;

import java.lang.reflect.Method;

public abstract class DefaultAspect {
    /**
     * @param targetClass 被代理的目标对象
     * @param method      需要进行代理的方法
     * @param args        方法需要的参数
     */
    public void before(Class<?> targetClass, Method method, Object[] args) throws Throwable {
    }

    /**
     * @param targetClass 被代理的目标对象
     * @param method      需要进行代理的方法
     * @param args        方法需要的参数
     * @param returnValue 方法返回的值
     * @return 方法执行后的返回值
     * @throws Throwable
     */
    public Object afterReturning(Class<?> targetClass, Method method, Object[] args, Object returnValue) throws Throwable {
        return returnValue;
    }

    /**
     * @param targetClass 被代理的目标对象
     * @param method      需要进行代理的方法
     * @param args        方法需要的参数
     * @param e           被代理方法抛出的异常
     * @throws Throwable
     */
    public void afterThrowing(Class<?> targetClass, Method method, Object[] args, Throwable e) throws Throwable {
    }
}
