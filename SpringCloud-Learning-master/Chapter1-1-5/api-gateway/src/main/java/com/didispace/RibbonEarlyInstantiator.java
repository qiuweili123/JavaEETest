package com.didispace;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.cloud.netflix.ribbon.apache.RibbonLoadBalancingHttpClient;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * Created by lenovo on 2018/1/26.
 */
@Component
public class RibbonEarlyInstantiator implements ApplicationListener<ApplicationStartedEvent> {
    @Autowired
    private ZuulProperties zuulProperties;
    @Autowired
    private SpringClientFactory springClientFactory;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        Map<String, ZuulProperties.ZuulRoute> routes = zuulProperties.getRoutes();
        routes.values()
                .stream()
                .filter(route -> route.getServiceId() != null)
                .map(route -> route.getServiceId())
                .distinct()
                .forEach(serviceId -> {
                    System.out.println("#####");
                    //   logger.info("Instantiating the context for the client '{}'", serviceId);
                    springClientFactory.getClient(serviceId, RibbonLoadBalancingHttpClient.class); //Or RestClient.class if you still use it
                });
    }

    @PostConstruct
    public void init() {

    }
}
