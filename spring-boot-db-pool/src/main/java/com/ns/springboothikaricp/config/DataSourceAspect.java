package com.ns.springboothikaricp.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 数据源AOP切面定义
 */
@Component
@Aspect
public class DataSourceAspect {
    //切换放在mapper接口的方法上，所以这里要配置AOP切面的切入点
    @Pointcut("execution( * com.ns.*.dao.*.*(..))")
    public void dataSourcePointCut() {
        System.out.println("###dddd############");
    }


    /*  @Before("dataSourcePointCut()")
      public void before(JoinPoint joinPoint) {

          Object target = joinPoint.getTarget();
          String method = joinPoint.getSignature().getName();
          Class<?>[] clazz = target.getClass().getInterfaces();
          Class<?>[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getMethod().getParameterTypes();
          try {
              Method m = clazz[0].getMethod(method, parameterTypes);
              //如果方法上存在切换数据源的注解，则根据注解内容进行数据源切换
              if (m != null && m.isAnnotationPresent(TargetDataSource.class)) {
                  TargetDataSource data = m.getAnnotation(TargetDataSource.class);
                  String dataSourceName = data.value();
                  DynamicDataSourceHolder.putDataSource(dataSourceName);
                  System.out.println("############################");
                  //log.debug("current thread " + Thread.currentThread().getName() + " add " + dataSourceName + " to ThreadLocal");
              } else {
                  //   log.debug("switch datasource fail,use default");
              }
          } catch (Exception e) {
              //log.error("current thread " + Thread.currentThread().getName() + " add data to ThreadLocal error", e);
          }
      }
  */
    @Pointcut("@annotation(com.ns.springboothikaricp.config.TargetDataSource)")
    public void annotationPointCut() {

    }

    @Before("dataSourcePointCut()&&@annotation(targetDataSource)")
    public void before2(JoinPoint joinPoint, TargetDataSource targetDataSource) {

        System.out.println("aop=====" + joinPoint.getSignature().getName());
    }

    @Before("@annotation(targetDataSource)")
    public void before(JoinPoint joinPoint, TargetDataSource targetDataSource) {

        System.out.println("#####");
        System.out.println("#############" + joinPoint.getSignature().getName());

//        String dataSourceName = targetDataSource.value();

        //  DynamicDataSourceHolder.putDataSource(dataSourceName);

    }

    @After("dataSourcePointCut()")
    public void after2(JoinPoint joinPoint) {
        System.out.println("----after---");
    }


    //执行完切面后，将线程共享中的数据源名称清空,一定要注意清除的时候要做判断
    @After("@annotation(targetDataSource)")
    public void after(JoinPoint joinPoint, TargetDataSource targetDataSource) {

        System.out.println("after....");
        // DynamicDataSourceHolder.removeDataSource();
    }


}
