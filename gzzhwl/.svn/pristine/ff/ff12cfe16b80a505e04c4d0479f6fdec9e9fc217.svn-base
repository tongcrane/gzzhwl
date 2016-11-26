package com.gzzhwl.rest.security.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.gzzhwl.rest.security.model.AuthType;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequirePerm {
	AuthType type() default AuthType.AUTHC;

	int[] value() default {};
}
