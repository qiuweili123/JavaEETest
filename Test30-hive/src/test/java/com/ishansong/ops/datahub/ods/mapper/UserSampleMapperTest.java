package com.ishansong.ops.datahub.log.mapper;

import com.ishansong.ops.datahub.ApplicationTests;
import org.junit.Test;
import org.sang.ods.mapper.UserSampleMapper;
import org.sang.ods.model.UserSample;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class UserSampleMapperTest extends ApplicationTests {

    @Resource
    private UserSampleMapper mapper;

    @Resource
    private JdbcTemplate odsHiveTemplate;

    @Test
    public void testFindByNum() {
        System.out.println("isnull==" + mapper.findByNum(112L));
    }

    @Test
    public void testInsert() {
        UserSample userSample = new UserSample();
        userSample.setUserAge(10);
        userSample.setUserGender("男");
        userSample.setUserName("张三");
        userSample.setUserNum(112L);
        System.out.println("insert ==" + mapper.insert(userSample));
    }

    @Test
    public void testTemplateQuery() throws SQLException {
        String sql = "select * from user_sample where user_num=112";
        List<Map<String, Object>> list = odsHiveTemplate.queryForList(sql);

        System.out.println("list==" + list);
    }


}
