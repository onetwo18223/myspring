package demo.annotation;

import java.lang.annotation.*;

/**
 * @author bhh
 * @description 测试在 类, 接口上的注解 定义
 * @date Created in 2021-04-26 15:08
 * @modified By
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TestAnnotationClass {
    public String value();
}
