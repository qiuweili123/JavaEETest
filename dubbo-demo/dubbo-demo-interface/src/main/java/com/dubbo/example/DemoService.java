package com.dubbo.example;

import io.swagger.annotations.ApiOperation;

public interface DemoService {

    Response<String> sayHello(String name);

    @ApiOperation(nickname = "byPhone", value = "查询用户", notes = "通过phone取用户信息", response = User.class, responseContainer = "List")
    Response<User> getById();

}