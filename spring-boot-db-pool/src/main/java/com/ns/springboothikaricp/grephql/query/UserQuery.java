package com.ns.springboothikaricp.grephql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.google.common.collect.Lists;
import com.ns.springboothikaricp.bean.User;
import com.ns.springboothikaricp.service.UserService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserQuery implements GraphQLQueryResolver {

    private UserService userService;
    public UserQuery(){
        userService=new UserService();
    }


    public List<User> getUsers(Long id){
        System.out.println("id=="+id);
        return Lists.newArrayList(new User());
    }

}
