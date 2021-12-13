package org.simpleframework.mvc.annotation;

import org.simpleframework.mvc.type.RequsetMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
    String value() default "";
    RequsetMethod method() default RequsetMethod.GET;

}
