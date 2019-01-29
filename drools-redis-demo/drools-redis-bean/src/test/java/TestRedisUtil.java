import com.alibaba.fastjson.JSON;
import com.ms.util.RedisUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2018/2/28.
 */
public class TestRedisUtil {
    public static void main(String[] args) {
        Map map = new HashMap();
        Map map1 = new HashMap();
        //   map1.put("1-2","阿司匹林不能与阿奇混用");
        InnerRoule rule1 = new InnerRoule("1-2", "阿司匹林不能与阿奇混用");
        InnerRoule rule2 = new InnerRoule("1-3", "阿司匹林不能与阿奇混用");
        map.put("1-2", JSON.toJSONString(rule1));
        map.put("1-3", JSON.toJSONString(rule2));


        //   RedisUtil.getInstance().hmset("againstRule",map);
        List<InnerRoule> againstRules = RedisUtil.getInstance().hmget(InnerRoule.class, "againstRule", "1-5");
        System.out.println("##" + againstRules);

    }

    static class InnerRoule {

        public InnerRoule() {

        }

        public InnerRoule(String key, String value) {
            this.key = key;
            this.value = value;
        }

        private String key;

        private String value;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
