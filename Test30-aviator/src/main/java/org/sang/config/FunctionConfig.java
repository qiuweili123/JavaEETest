package org.sang.config;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.spring.SpringContextFunctionLoader;
import org.sang.function.AddFunction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

//@Configuration
public class FunctionConfig {
/*    @Resource
    private ApplicationContext applicationContext;

    @Bean
    public SpringContextFunctionLoader springContextFunctionLoader() {
        SpringContextFunctionLoader loader = new SpringContextFunctionLoader(applicationContext);
        AviatorEvaluator.addFunctionLoader(loader);
        return loader;
    }*/
}
