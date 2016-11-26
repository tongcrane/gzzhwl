package com.gzzhwl.excel.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface ExcelFieldMappingByPosition {
    int position();

    String importDataFormat() default "yyyy-MM-dd HH:mm:ss";
}