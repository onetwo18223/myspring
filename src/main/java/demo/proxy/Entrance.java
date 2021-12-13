package demo.proxy;

import demo.proxy.cglib.CGLibMethodInterceptor;
import demo.proxy.cglib.until.CGLIbTestExt;
import demo.proxy.cglib.until.CGLibUtils;

public class Entrance {
    public static void main(String[] args) {

//        DoStudy doStudy = new DoStudy();
//        DoStudyInvocationHandler doStudyInvocationHandler = new DoStudyInvocationHandler(doStudy);
//        DoStudyService proxyInstance = InvocationHandlerUntil.newProxyInstance(doStudy, doStudyInvocationHandler);
//        proxyInstance.study();
//
        CGLIbTestExt cglIbTestExt = new CGLIbTestExt();
        CGLibMethodInterceptor cgLibMethodInterceptor = new CGLibMethodInterceptor();
        CGLIbTestExt cglibProxy = CGLibUtils.createCglibProxy(cglIbTestExt, cgLibMethodInterceptor);
        cglibProxy.study();

//        DoStudyService proxyObj = (DoStudyService) Proxy.newProxyInstance(doStudy.getClass().getClassLoader(), doStudy.getClass().getInterfaces(), new InvocationHandler() {
//            @Override
//            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                System.out.println("1111");
//                Object invokeObj = method.invoke(doStudy, args);
//                System.out.println(22222);
//                return invokeObj;
//            }
//        });
//
//        System.out.println(proxyObj);
//        proxyObj.study();


    }
}
