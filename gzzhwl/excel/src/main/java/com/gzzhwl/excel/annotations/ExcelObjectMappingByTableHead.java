package com.gzzhwl.excel.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.gzzhwl.excel.enums.ParseType;

/**
 * 
 * Excel Object annotation should be used at Class level with the below fields populated.
 * 
 * @author Venkatesh Nannan.
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface ExcelObjectMappingByTableHead {

    String sheetName() default "";

    int sheetIndex() default 0;

    /**
     * Parse type should be set to either Row level parsing or column level parsing.
     * 
     * @return ParseType.
     */
    ParseType parseType();

    int headPosition();

    int headStart();

    int headEnd() default 0;

    /**
     * Start of the block. eg. If you want to parse Rows 8 to 408 (400 rows), this field should be set as 8.
     * 
     * @return int.
     */
    int dataStart();

    /**
     * End of the block. eg. If you want to parse Rows 8 to 408 (400 rows), this field should be set as 408.
     * 
     * @return int.
     */
    int dataEnd() default 0;

    /**
     * Setting this field to true will return Zero for Number fields (Double, Integer, Long) when the data is not found
     * while parsing.
     * 
     * @return boolean.
     */
    boolean zeroIfNull() default false;

    /**
     * This field should be set to true when you want to ignore the row or column having just Zero or NULL data.
     * 
     * @return boolean.
     */
    // boolean ignoreAllZerosOrNullRows() default false;
}