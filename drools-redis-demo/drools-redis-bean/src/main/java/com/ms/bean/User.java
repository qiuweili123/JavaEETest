package com.ms.bean;

import com.ms.util.RedisUtil;

/**
 * Created by lenovo on 2018/1/23.
 */
public class User {


    private Long id;
    private String name;
    private Integer age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return Integer.valueOf(RedisUtil.getInstance().get("user:" + id));
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
