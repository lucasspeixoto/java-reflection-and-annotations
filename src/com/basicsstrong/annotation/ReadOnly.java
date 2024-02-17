package com.basicsstrong.annotation;

import static java.lang.annotation.ElementType.*;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target({TYPE_PARAMETER, TYPE_USE})
public @interface ReadOnly {
}
