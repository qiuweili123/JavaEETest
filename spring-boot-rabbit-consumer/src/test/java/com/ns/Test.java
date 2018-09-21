package com.ns;

import com.alibaba.fastjson.JSON;

/**
 * Created by lenovo on 2017/11/14.
 */
public class Test {

    public static void main(String[] args) {
        String msg = "{\"name\":\"name:70969114-527a-4f97-9398-ebad51eb45ec\"}";
        User user = JSON.parseObject(msg, User.class);
        System.out.println("user=" + user);
    }
}
