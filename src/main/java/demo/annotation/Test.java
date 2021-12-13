package demo.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author bhh
 * @description 测试类
 * @date Created in 2021-04-26 15:18
 * @modified By
 */
public class Test {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException {
        Class<TestExterior> clazz =
                (Class<TestExterior>) Class.forName("com.bhh.demo.annotation.TestExterior");

        Field field = clazz.getDeclaredField("wantTo");
        TestAnnotationFiled annotation = field.getAnnotation(TestAnnotationFiled.class);
        System.out.println("annotation.judge() = " + annotation.judge());

        Method method = clazz.getDeclaredMethod("method");
        TestAnnotationMethod annotation1 = method.getAnnotation(TestAnnotationMethod.class);
        System.out.println("annotation1.num() = " + annotation1.num());

        TestAnnotationClass annotation2 = clazz.getAnnotation(TestAnnotationClass.class);
        System.out.println("annotation2.value() = " + annotation2.value());

        /**
         * 总结 :
         * 我们无法直接获取到注解上的信息
         *
         * 在反射出的类中, 像 Class类, Field类, Method类 查看其源码可以发现 都是直接或者间接 实现了接口AnnotatedElement
         * 而这个接口作用便是 获取被作用的接口的对象
         * 例如方法
         * isAnnotationPresent(Class<? extends Annotation> annotationClass) : 查看元素上是否有指定注解
         * getAnnotation(Class<T> annotationClass) 获取元素上指定注解, 可以包括被继承的注解
         * getAnnotations() 获取元素上所有注解, 包括继承的注解
         *
         * 查看源码后, 我们得知, 我们可以通过 Class类, Field类, Method类 通过 调用指定来获取 Annotation注解对象
         *
         * 注意 : 如果注解 使用范围不是 RetentionPolicy.RUNTIME, 那么就无法通过 反射获取到注解对象
         *
         * 注意 : 在程序运行时, jdk 会利用动态代理 为注解生成代理类 (生命周期为RunTime的注解)
         *
         * 注解的原理 :
         *      编译器检查注解的使用范围, 会将注解信息写入元素属性表
         *      运行时, JVM 会取出( 一个class文件内 )所有 生命周期为 RUNTIME的注解, 存储到一个map中
         *      创建 AnnotationInvocationHandler (代理处理器)(代理执行器是Proxy.newProxyInstance())
         *      JVM 动态生成 注解的动态代理类
         *
         */
    }
}
