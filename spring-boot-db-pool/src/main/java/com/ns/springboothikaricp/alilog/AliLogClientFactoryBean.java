package com.ns.springboothikaricp.alilog;

import com.ns.springboothikaricp.client.ALilogImpl;
import org.springframework.beans.factory.FactoryBean;

public class AliLogClientFactoryBean<T> implements FactoryBean<T> {
    private final Class<T> interfaceClass;

    public AliLogClientFactoryBean(Class<T> interfaceClass) {
        this.interfaceClass = interfaceClass;
    }

    @Override
    public T getObject() throws Exception {
        return (T) new ALilogImpl();
    }

    @Override
    public Class<?> getObjectType() {
        return interfaceClass;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
