package com.ns.springboothikaricp.grephql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.ns.springboothikaricp.service.UserService;

public class UserQuery implements GraphQLQueryResolver {

    private UserService userService;
    public UserQuery(){
        userService=new UserService();
    }




}
