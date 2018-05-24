package com.ns.springboothikaricp.controller;

import com.ns.springboothikaricp.dao.UserInfoMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserInfoMapper userInfoMapper;

    @GetMapping("/getById")
    public Object getById(long id) {
        System.out.println("id==" + (id % 2));
        if ((id % 2) == 0) {
            return userInfoMapper.selectByEvenUserId(id);
        } else {

            return userInfoMapper.selectByOddUserId(id);
        }
    }
}
