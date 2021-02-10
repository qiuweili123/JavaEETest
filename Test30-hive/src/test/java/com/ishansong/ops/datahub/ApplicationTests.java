package com.ishansong.ops.datahub;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.sang.ods.Application;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles("DS")
public class ApplicationTests {
    @Test
    public void contextLoads() {

    }

}
