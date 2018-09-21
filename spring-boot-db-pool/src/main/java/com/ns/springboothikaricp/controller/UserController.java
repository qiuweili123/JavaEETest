package com.ns.springboothikaricp.controller;

import com.ns.common.ServiceException;
import com.ns.springboothikaricp.bean.User;
import com.ns.springboothikaricp.constants.PathConstants;
import com.ns.springboothikaricp.dao.UserInfoMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Api(value = "/test", tags = "测试接口模块")
@RestController
@RequestMapping(PathConstants.API + "/user")

public class UserController {
    private static final Logger APP_LOGGER = LoggerFactory.getLogger("app");

    @Resource
    private UserInfoMapper userInfoMapper;



   /* @Resource
    private UserDao userDao;*/

    @RequestMapping("/getById")
    public Object getById(long id) {
        //userDao.getName();
        System.out.println("##tmp::"+System.getProperty("java.io.tmpdir"));

        APP_LOGGER.info("id=4444===44=" + (id % 2));

        APP_LOGGER.info("sleep end");
        User user;
        if ((id % 2) == 0) {
            user = userInfoMapper.selectByEvenUserId(id);
        } else {

            user = userInfoMapper.selectByOddUserId(id);
        }
        //Optional.ofNullable(user).orElseThrow(()-> new ServiceException("user weikong","001"));

        if (user == null) {
            throw new ServiceException("user weikong", "001");
        }
        return user;
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

    @RequestMapping("/getBySEId")
    public Object getBySEId(long id) {
        throw new ServiceException("错误信息", "2001");
    }

    @RequestMapping("/getByEId")
    public Object getByEId(long id) {

        int n = 1 / 0;
        return null;
    }

    @GetMapping("/getByUserId")
    public Object getByUserId(String email) {
        System.out.println("##email==" + email);
        return email;
    }


}
