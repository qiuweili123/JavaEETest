package com.ns.springboothikaricp.bean;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(description = "用户表信息")
public class User {

    @ApiModelProperty("主键ID")
    private Integer id;
    @ApiModelProperty("用户名称")
    private String userName;
    @ApiModelProperty("用户年龄")
    private Integer userAge;
    @ApiModelProperty("用户地址")
    private String address;
    @ApiModelProperty("创建时间")
    private Date addTime;
    @ApiModelProperty("备注")
    private String remarks;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
