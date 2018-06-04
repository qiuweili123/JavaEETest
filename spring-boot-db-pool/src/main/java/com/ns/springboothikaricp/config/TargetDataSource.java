package com.ns.springboothikaricp.config;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * 目标数据源注解，注解在方法上指定数据源的名称
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({TYPE, FIELD, METHOD})
public @interface TargetDataSource {
    String value();//此处接收的是数据源的名称
}
