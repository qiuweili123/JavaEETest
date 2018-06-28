package com.ns.springboothikaricp.controller;

import com.ns.springboothikaricp.bean.User;
import com.ns.springboothikaricp.constants.PathConstants;
import com.ns.springboothikaricp.dao.UserInfoMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(value = "/test", tags = "测试接口模块")
@RestController
@RequestMapping(PathConstants.API + "/user")

public class UserController {
    @Resource
    private UserInfoMapper userInfoMapper;



   /* @Resource
    private UserDao userDao;*/

    @GetMapping("/getById")
    public Object getById(long id) {
        //userDao.getName();
        System.out.println("id=4444===44=" + (id % 2));
        if ((id % 2) == 0) {
            return userInfoMapper.selectByEvenUserId(id);
        } else {

            return userInfoMapper.selectByOddUserId(id);
        }
    }

    @ApiOperation(value = "添加用户信息", notes = "添加用户信息")
    @ApiImplicitParam(name = "user", value = "User", required = true, dataType = "User", defaultValue = "user_name:")
    @ApiResponse(code = 1, message = "成功", response = User.class)
    @PostMapping("/addUser")
    public Object addUser(@RequestBody User user) {
//返回自增主键
        int ret = userInfoMapper.insert(user);
        System.out.println("id==" + user.getId() + "##ret==" + ret);

        return "success";
    }
}
