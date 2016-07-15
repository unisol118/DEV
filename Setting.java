package com.am.generic;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME) // The annotation will be available @runtime

@Target({ElementType.TYPE,ElementType.METHOD,ElementType.FIELD,
	 ElementType.CONSTRUCTOR,ElementType.ANNOTATION_TYPE,
	 ElementType.PACKAGE})    // where can we use this annotation i,e method level , field level, class level


public @interface Setting {
	
	public boolean isDynamic() default true;
	public String name() default "test";
	public String value() default "";

}
