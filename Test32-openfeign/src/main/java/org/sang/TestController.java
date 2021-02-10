package org.sang;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private PlatformClient client;

    @GetMapping("/mobile")
    public Object mobile(String mobile){
        return  client.decryptMobile(mobile);
    }
}
