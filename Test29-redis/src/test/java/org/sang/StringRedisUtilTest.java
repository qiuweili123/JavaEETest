package org.sang;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.sang.bean.Person;
import org.sang.util.RedisUtil;

import javax.annotation.Resource;

public class StringRedisUtilTest extends ApplicationTests {
    @Resource
    private RedisUtil stringRedisUtil;
    @Test
    public void setAndInc() {
        String key = "intKey01";
        stringRedisUtil.set(key, 100);

        //System.out.println(redisDao.incr(key,10)+"##get ddd ::"+redisDao.get(key,Integer.class));
    }

    /**
     * 同样的一个对象使用String序列化对象要比json序列化对象减少30字节大小内存空间，原因在于value中多了类的类型"@type": "org.sang.bean.Person",去掉类型后，大小均是50k
     * 为方便操作value推荐使用json进行存储
     */
    @Test
    public void setAndGetUser() {
        Person user = new Person();
        user.setId(222L);
        user.setName("zhangsan");
        user.setAddress("北京市");
        String key = "user:" + user.getId();
        stringRedisUtil.set(key, JSON.toJSONString(user));
    }


}
