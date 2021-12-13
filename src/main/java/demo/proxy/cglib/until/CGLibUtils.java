package demo.proxy.cglib.until;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

public class CGLibUtils {
    public static<T> T createCglibProxy(T obj, MethodInterceptor methodInterceptor){
        Object proxyObj = Enhancer.create(obj.getClass(), methodInterceptor);
        return (T)proxyObj;
    }
}
