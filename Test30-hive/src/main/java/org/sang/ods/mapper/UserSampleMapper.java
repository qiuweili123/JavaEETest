package org.sang.ods.mapper;


import org.sang.ods.model.UserSample;

public interface UserSampleMapper extends SysMapper<UserSample> {
    default  UserSample findByNum(Long num){
     return this.selectOne(getLambdaQuery().eq(UserSample::getUserNum,num));
    }
}
