package com.didispace;

import com.netflix.client.RetryHandler;
import com.netflix.client.config.IClientConfig;
import org.springframework.cloud.netflix.ribbon.apache.RibbonLoadBalancingHttpClient;
import org.springframework.context.annotation.Bean;

class DefaultConfig {
    @Bean
    public RibbonLoadBalancingHttpClient ribbonLoadBalancingHttpClient(IClientConfig config, RetryHandler retryHandler) {

        System.out.println("-----------------init-----------");
        RibbonLoadBalancingHttpClient client = new RibbonLoadBalancingHttpClient();
        client.initWithNiwsConfig(config);
        client.setRetryHandler(retryHandler);
        return client;
    }
}