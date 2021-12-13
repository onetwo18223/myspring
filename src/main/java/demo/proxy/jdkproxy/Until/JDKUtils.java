package demo.proxy.jdkproxy.Until;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class JDKUtils {
    public static<T> T newProxyInstance(T objectTarget, InvocationHandler invocationHandler){
        ClassLoader classLoader = objectTarget.getClass().getClassLoader();
        Class<?>[] interfaces = objectTarget.getClass().getInterfaces();
        return (T)Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
    }
}
