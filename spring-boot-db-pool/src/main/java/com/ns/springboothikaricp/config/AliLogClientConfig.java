package com.ns.springboothikaricp.config;


import com.aliyun.openservices.log.Client;
import com.ns.springboothikaricp.alilog.AliLogBeanRegistry;
import com.ns.springboothikaricp.annotation.AliLogClient;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Set;

/*
* 通过动态代理动态实现优雅调用API
* */
@Configuration
public class AliLogClientConfig {

    @Bean("aliLogClient")
    public static Client aliLogClient(ConfigurableEnvironment envConfig) {
        //获取这种属性的另外的方式就是@ConfigurationProperties(prefix = "ali-log")
        // String url = envConfig.getProperty("ali-log.url");// 这种方式可以获取注入的值
        String url = envConfig.resolvePlaceholders("${ali-log.url:}");//另外的一种取值方式

        String accessKeyId = envConfig.resolvePlaceholders("${ali-log.access-key-id}");
        String accessKeySecret = envConfig.resolvePlaceholders("${ali-log.access-key-secret}");

        return new Client(url, accessKeyId, accessKeySecret);
    }

    @Bean
    public AliLogBeanRegistry registry(){
        return  new AliLogBeanRegistry();
    }
    /*@Bean
    public   BeanScannerConfig beanScannerConfig() {
        return new BeanScannerConfig();
    }


    public   class BeanScannerConfig implements BeanDefinitionRegistryPostProcessor {


        @Override
        public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
            System.out.println("beans count::" + beanDefinitionRegistry.getBeanDefinitionCount());
            Scanner scanner = new Scanner(beanDefinitionRegistry);
            scanner.doScan("com.ns.springboothikaricp.client");
            System.out.println("after beans count::" + beanDefinitionRegistry.getBeanDefinitionCount());
        }

        @Override
        public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
            System.out.println("new beans count::" + configurableListableBeanFactory.getBeanDefinitionCount());
        }


    }

    private   class Scanner extends ClassPathBeanDefinitionScanner {

        public Scanner(BeanDefinitionRegistry registry) {
            super(registry);
        }

        @Override
        public void registerDefaultFilters() {
            this.addIncludeFilter(new AnnotationTypeFilter(AliLogClient.class));
        }

        @Override
        public Set<BeanDefinitionHolder> doScan(String... basePackages) {
            Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);
            beanDefinitions.forEach(beanDefinitionHolder -> {
                GenericBeanDefinition beanDefinition = (GenericBeanDefinition) beanDefinitionHolder.getBeanDefinition();
                beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(beanDefinition.getBeanClassName());
                System.out.println("before class name::" + beanDefinition.getBeanClassName());
                beanDefinition.getPropertyValues().add("innerClassName", beanDefinition.getBeanClassName());
                beanDefinition.setBeanClass(AliLogClientFactoryBean.class);
            });
            return beanDefinitions;
        }

        @Override
        public boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
            return beanDefinition.getMetadata().hasAnnotation(AliLogClient.class.getName());
        }
    }

    private class AliLogClientFactoryBean<T> implements FactoryBean<T> {

        private final Class<T> classInterface;


        private String innerClassName;

        public AliLogClientFactoryBean(Class<T> classInterface) {
            this.classInterface = classInterface;
        }

        public void setInnerClassName(String innerClassName) {
            this.innerClassName = innerClassName;
        }

        @Override
        public T getObject() throws Exception {
            System.out.println("construtor");
            return (T) getObjectType().newInstance();
        }

        @Override
        public Class<?> getObjectType() {
            try {
                System.out.println("innerClassName::" + innerClassName);
                return classInterface;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public boolean isSingleton() {
            return true;
        }
    }

    private static class AliLogHandler implements InvocationHandler {
        public static <T> T newInstance(Class<T> innerInterface) {
            ClassLoader classLoader = innerInterface.getClassLoader();
            Class[] interfaces = new Class[]{innerInterface};
            System.out.println("##==" + innerInterface);
            AliLogHandler handler = new AliLogHandler();
            return (T) Proxy.newProxyInstance(classLoader, interfaces, handler);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return method.invoke(proxy, args);
        }
    }
*/

}
