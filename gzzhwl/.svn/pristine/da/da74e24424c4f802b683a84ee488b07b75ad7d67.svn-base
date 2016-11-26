package com.gzzhwl.excel.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface ExcelFieldMappingByHeadName {
	String headName();

	String importDataFormat() default "yyyy-MM-dd HH:mm:ss";

	String exportDataFormat() default "yyyy-MM-dd HH:mm:ss";

	int exportFieldWidth() default 12;

	boolean notExport() default false;
}