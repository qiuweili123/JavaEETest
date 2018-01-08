package org.sang.contorller;

import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by lenovo on 2017/12/3.
 */
@RestController
@RequestMapping("/springboot")
public class HelloControlller {
    @Resource
    private CounterService counterService;
    @Resource
    private GaugeService gaugeService;

    @RequestMapping("/hello")
    public String hello() {
        counterService.increment(this.getClass().getName()+",method hello");
        gaugeService.submit("hello",1);
        return "hello";
    }
}
