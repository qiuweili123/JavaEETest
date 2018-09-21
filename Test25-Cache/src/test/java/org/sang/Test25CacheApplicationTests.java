package org.sang;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test25CacheApplicationTests {
    @Resource
    private DemoService demoService;

    @Test
    public void contextLoads() {
        System.out.println("demo==" + demoService);
    }


}
