package com.innoppl.outreach.data.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Jerald Mejarla
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface InjectMethods {

    /**
     * Methods to include
     * @return {@link Method}. If not specified, defaults to {@link Method}.ALL.
     */
    Method[] include() default Method.ALL;

    /**
     * Methods to exclude
     * @return {@link Method}. If not specified, defaults to {@link Method}.NONE.
     */
    Method[] exclude() default Method.NONE;
}
