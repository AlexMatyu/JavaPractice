package org.vtb.practice.task04.logging;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface LogTransformation {
    String value() default "c:\\!Matyu\\Temp\\LogTransformation\\LogTransformation.log";
}
