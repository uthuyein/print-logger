package com.jdc.mkt.utils.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Connector {

	String value() default "MYSQL";
	String name() default "testDb";
	String user() default "root";
	String password() default "admin";
}
