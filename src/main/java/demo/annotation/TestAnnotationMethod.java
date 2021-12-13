package demo.annotation;

import java.lang.annotation.*;

/**
 * @author bhh
 * @description 测试在方法上的注解 定义
 * @date Created in 2021-04-26 14:07
 * @modified By
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TestAnnotationMethod {
    public int num();
}

