package com.bhh.aspect;

import lombok.extern.slf4j.Slf4j;
import org.simpleframework.aop1.annotation.Aspect;
import org.simpleframework.aop1.annotation.Order;
import org.simpleframework.aop1.aspect.DefaultAspect;
import org.simpleframework.core.annotation.Controller;

import java.lang.reflect.Method;

@Slf4j
@Aspect(Controller.class)
@Order(0)
public class ControllerAspect extends DefaultAspect {
    private long startTime;

    @Override
    public void before(Class<?> targetClass, Method method, Object[] args) throws Throwable {
        log.debug("开始计时，执行的类是[{}],执行的方法是[{}], 参数是[{}]",
                targetClass.getName(), method.getName(), args
        );
        startTime = System.currentTimeMillis();
    }

    @Override
    public Object afterReturning(Class<?> targetClass, Method method, Object[] args, Object returnValue) throws Throwable {
        long endTime = System.currentTimeMillis();

        log.debug("结束计时，执行的类是[{}],执行的方法是[{}], 参数是[{}], 计时结果是[{}]",
                targetClass.getName(), method.getName(), args, endTime - startTime);

        return returnValue;
    }


}
