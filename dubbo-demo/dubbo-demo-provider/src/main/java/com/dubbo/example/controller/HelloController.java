package com.dubbo.example.controller;

import com.dubbo.example.DemoService;
import com.dubbo.example.Response;
import com.dubbo.example.User;
import com.dubbo.example.provider.DemoServiceImpl;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
@Controller
@Service(version = "1.0.0")
public class HelloController implements DemoService {

    @Resource
    private DemoServiceImpl service;

    @Override
    public Response<String> sayHello(String name) {
        Response response = new Response();
        response.setData(service.sayHello(name));
        return response;
    }

    @Override
    public Response<User> getById() {

        User u=new User();
        u.setName("zhangsang");
        Response response = new Response();
        response.setData(u);
        return response;
    }
}
