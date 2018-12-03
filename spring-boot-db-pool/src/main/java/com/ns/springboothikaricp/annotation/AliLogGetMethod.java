package com.ns.springboothikaricp.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface AliLogGetMethod {

    String value()  default "" ;


}
