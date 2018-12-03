package com.ns.springboothikaricp.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface AliLogClient {

    String project()  ;

    String logstore();

}
