package demo.annotation;


import java.lang.annotation.*;

/**
 * @author bhh
 * @description 测试在属性上的注解 定义
 * @date Created in 2021-04-26 15:09
 * @modified By
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TestAnnotationFiled {
    public boolean judge() default true;
}
