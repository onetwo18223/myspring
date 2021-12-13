package org.simpleframework.mvc.type;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * 待执行的controller方法实例以及参数的映射
 */
public class ControllerMethod {
    //Controller对应的对象
    private Class<?> controllerClass;
    private Method invokeMethod;
    private Map<String, Class<?>> methodParameters;
}
