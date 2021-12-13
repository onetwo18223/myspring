package demo.proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CGLibMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        befor();
        Object invokeObj = methodProxy.invokeSuper(o, args);
        after();
        return invokeObj;
    }
    public void befor(){ System.out.println("before cglib"); }
    public void after(){ System.out.println("after cglib"); }

}
