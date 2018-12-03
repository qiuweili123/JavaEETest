package com.ns.springboothikaricp.alilog;

import com.ns.springboothikaricp.annotation.AliLogClient;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.classreading.MetadataReader;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class AliLogDefinitionScanner extends ClassPathBeanDefinitionScanner {
    public AliLogDefinitionScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    @Override
    protected boolean isCandidateComponent(MetadataReader metadataReader) throws IOException {
        return metadataReader.getAnnotationMetadata().hasAnnotation(AliLogClient.class.getName());
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().isIndependent() && beanDefinition.getMetadata().isInterface();
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitionHolders = super.doScan(basePackages);
        for (BeanDefinitionHolder definitionHolder : beanDefinitionHolders) {
            GenericBeanDefinition definition = (GenericBeanDefinition) definitionHolder.getBeanDefinition();
            //以下操作fatorybean
            //调用factorybean构造函数
            //小彩蛋，这里塞的是 String ，而我们的构造器参数其实要的是 Class 。但是 Spring 的 ConstructorResolver.autowireConstructor 中用到了 Object[] argsToUse 去做了个转换 。
            //可以参照mybeities spring的实现过程
            definition.getConstructorArgumentValues().addGenericArgumentValue(definition.getBeanClassName());
            List<ConstructorArgumentValues.ValueHolder> genericArgumentValues = definition.getConstructorArgumentValues().getGenericArgumentValues();
            // definition.getConstructorArgumentValues().addGenericArgumentValue(definition.getBeanClassName());

            definition.setBeanClass(AliLogClientFactoryBean.class);
        }
        return beanDefinitionHolders;
    }
}
