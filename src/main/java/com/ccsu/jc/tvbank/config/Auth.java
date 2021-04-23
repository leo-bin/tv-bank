package com.ccsu.jc.tvbank.config;

import java.lang.annotation.*;

@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)

public @interface Auth {
	 boolean validate() default true;
}
