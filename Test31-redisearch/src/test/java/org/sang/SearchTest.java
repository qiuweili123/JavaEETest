package org.sang;

import com.redislabs.lettusearch.StatefulRediSearchConnection;
import com.redislabs.lettusearch.search.SearchResults;
import io.lettuce.core.protocol.RedisCommand;
import org.junit.Test;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.Collection;

public class SearchTest extends ApplicationTests {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    StatefulRediSearchConnection<String, String> connection;
    @Test
    public void testSearch() {
        SearchResults<String, String> search = connection.sync().search("myIdx", "hello");


        System.out.println(search);
    }

    /**
     *  connection.sync()
     */
    @Test
    public void testArg(){
     //   StatefulRediSearchConnection<String, String> statefulConnection = connection.sync().getStatefulConnection();
        //Object get = redisTemplate.getConnectionFactory().getConnection().execute("get", "hello".getBytes());

       //"myIdx"hello world" LIMIT  0 10

      /*  Object get = redisTemplate.getConnectionFactory().getConnection().execute(" FT.SEARCH", "hello".getBytes());
        System.out.println(get);
*/
    }
}
