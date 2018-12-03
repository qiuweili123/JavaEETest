package com.ns.springboothikaricp.grephql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.google.common.collect.Lists;
import com.ns.springboothikaricp.bean.Pet;
import com.ns.springboothikaricp.bean.User;
import com.ns.springboothikaricp.constants.Animal;
import com.ns.springboothikaricp.service.UserService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserQuery implements GraphQLQueryResolver {

    private UserService userService;
    public UserQuery(){
        userService=new UserService();
    }

    /**
     * 请求方式
     * {
     *   getUsers(id: 10) {
     *     id
     *     userName
     *   }
     * }
     * @param ids
     * @return
     */
    public List<User> getUsers(List<Long> ids){
        System.out.println("id=="+ids);
        User user1 = new User();
        user1.setUserName("zhangsan");
        user1.setId(1);
        user1.setAddress("beijingshi haidianqu");
        User user2 = new User();
        user2.setUserName("list");
        user2.setId(2);
        user2.setAddress("beijingshi chayang");
        return Lists.newArrayList(user1,user2);
    }


    public User user(Long id){
        User user1 = new User();
        user1.setUserName("zhangsan");
        user1.setId(1);
        user1.setAddress("beijingshi haidianqu");
        return  user1;
    }


    public List<Pet> pets() {
        List<Pet> pets = new ArrayList<>();
        Pet aPet = new Pet();
        aPet.setId(1l);
        aPet.setName("Bill");
        aPet.setAge(9);
        aPet.setType(Animal.MAMMOTH);
        pets.add(aPet);
        return pets;
    }

    /**
     * 请求方式
     *query{
     *   findAllUsers{
     *     userName
     *   }
     * }
     * 或者 去掉query
      * @return
     */

    public List<User> findAllUsers(){
        return getUsers(Lists.newArrayList(1L,2L));
    }

}
