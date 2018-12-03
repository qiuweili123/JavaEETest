package com.ns.springboothikaricp;

import com.ns.springboothikaricp.grephql.query.UserQuery;
import org.junit.Test;

public class GraphsqlTest extends ApplicationTests {

    private UserQuery userQuery;

    @Test
    public  void testGet(){
        System.out.println("null::"+userQuery==null);
    }

}
