package demo.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DoStudyInvocationHandler implements InvocationHandler {
    private Object objectTarget;
    public DoStudyInvocationHandler(Object objectTarget){
        this.objectTarget = objectTarget;
    }

    /**
     *
     * @param proxy 代理对象
     * @param method 执行的方法
     * @param args 需要的参数
     * @return 返回代理对象
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        who();
        Object objProxy = method.invoke(objectTarget, args);
        what();
        return objProxy;
    }

    public void who(){
        System.out.println("谁她妈买小米");
    }
    public void what(){
        System.out.println("窝窝头一块钱四个");
    }

}
