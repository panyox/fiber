package com.fiber.rpc.dubbo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Fiber service
 *
 * @author panyox
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface FiberService {

    String value() default "";

    String version() default "";

    String desc() default "";

}
