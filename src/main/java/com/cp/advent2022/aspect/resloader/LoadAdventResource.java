package com.cp.advent2022.aspect.resloader;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for subclasses of {@link com.cp.advent2022.api.DayApi DayApi} to automatically populate puzzle input
 * lines.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface LoadAdventResource {

}
