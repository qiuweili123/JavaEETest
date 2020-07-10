package com.ns.springboothikaricp.controller;

import com.ishansong.ops.rinet.client.RiskClient;
import com.ns.common.ServiceException;
import com.ns.springboothikaricp.bean.User;
import com.ns.springboothikaricp.constants.PathConstants;
import com.ns.springboothikaricp.dao.UserInfoMapper;
import com.ns.springboothikaricp.grephql.GraphQLType;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLSchema;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.Map;

import static graphql.Scalars.GraphQLInt;
import static graphql.Scalars.GraphQLLong;
import static graphql.schema.GraphQLObjectType.newObject;

@Api(value = "/test", tags = "测试接口模块")
@RestController
@RequestMapping(PathConstants.API + "/user")

public class UserController extends BaseContoller {
    private static final Logger APP_LOGGER = LoggerFactory.getLogger("risk");
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserInfoMapper userInfoMapper;



   /* @Resource
    private UserDao userDao;*/

    @RequestMapping("/getById")
    public Object getById(long id) {
        //userDao.getName();

        System.out.println("##tmp::" + System.getProperty("java.io.tmpdir"));
        LOGGER.info("sdsd");
        APP_LOGGER.info("id=4444===44=" + (id % 2));

        RiskClient riskClient=new RiskClient();
       // APP_LOGGER.info("");
        Map map=new HashMap();
        map.put("key","201908929");
         riskClient.finish("201908929");

        User user;
     try {
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
     } finally {
         System.out.println("ret end....");
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

    @RequestMapping("/getGraphQLById")
    public Object getGraphQLById() {
        String   ghql="{user(id:1){id,userName}}";
        GraphQLFieldDefinition personDefinition =
                GraphQLFieldDefinition.newFieldDefinition()
                        .name("user")
                        .type(GraphQLType.userGraphQLType)//绑定某一类型
                        .argument(GraphQLArgument.newArgument().name("id").type(GraphQLLong))//参数类型

                        .dataFetcher(environment -> {
                            long id1 = environment.getArgument("id");
                            return userInfoMapper.selectByOddUserId(id1);
                        }).build();

        GraphQLSchema schema = GraphQLSchema.newSchema()
                .query(newObject()
                        .name("userQuery")
                        .field(personDefinition)
                        .build())
                .build();

        GraphQL graphQL=GraphQL.newGraphQL(schema).build();
        ExecutionResult executionResult = graphQL.execute(ghql);

        Map<String, Object> result = executionResult.getData();
        //GraphQLType graphQL = GraphQL(schema).build();
        APP_LOGGER.info("ql:{}",result);
        return result;
    }

}
