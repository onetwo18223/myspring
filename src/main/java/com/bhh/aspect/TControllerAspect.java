package com.bhh.aspect;

import lombok.extern.slf4j.Slf4j;
import org.simpleframework.aop1.annotation.Aspect;
import org.simpleframework.aop1.annotation.Order;
import org.simpleframework.aop1.aspect.DefaultAspect;
import org.simpleframework.core.annotation.Controller;

import java.lang.reflect.Method;

@Slf4j
@Aspect(Controller.class)
@Order(10)
public class TControllerAspect extends DefaultAspect {
    @Override
    public void before(Class<?> targetClass, Method method, Object[] args) throws Throwable {
        log.debug("开始运行，执行的类是[{}],执行的方法是[{}], 参数是[{}], Hello,Spring",
                targetClass.getName(), method.getName(), args);
    }

    @Override
    public Object afterReturning(Class<?> targetClass, Method method, Object[] args, Object returnValue) throws Throwable {
        long endTime = System.currentTimeMillis();

        log.debug("结束运行，执行的类是[{}],执行的方法是[{}], 参数是[{}], Bye,Spring",
                targetClass.getName(), method.getName(), args);

        return returnValue;
    }
}
