package org.sang.util;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 说明：GenericJackson2JsonRedisSerializer性能没有FastJsonRedisSerializer和Jackson2JsonRedisSerializer性能要好,Jackson2JsonRedisSerializer存在反序列失败的问题.
 * GenericJackson2JsonRedisSerializer会将基本类型Type保存到redis，增加redis的使用内存。如果存储的对象的属性缺少了，将反序列化失败
 * 推荐使用GenericFastJsonRedisSerializer
 */


public class RedisUtil {
    private final String MUTEX_KEY = "mutex";
    private final static RedisSerializer DEFAULT_STRING_SERIALIZER = new StringRedisSerializer();
    private final static RedisSerializer DEFAULT_JSON_SERIALIZER = new GenericFastJsonRedisSerializer();

    private RedisTemplate redisTemplate;

    public RedisUtil(RedisTemplate redisTemplate) {
        redisTemplate.setKeySerializer(DEFAULT_STRING_SERIALIZER);
        redisTemplate.setValueSerializer(DEFAULT_JSON_SERIALIZER);
        redisTemplate.setHashKeySerializer(DEFAULT_STRING_SERIALIZER);
        redisTemplate.setHashKeySerializer(DEFAULT_STRING_SERIALIZER);
        this.redisTemplate = redisTemplate;
    }

    public void setHashKeySerializer(RedisSerializer hashKeySerializer) {
        redisTemplate.setHashKeySerializer(hashKeySerializer);
    }

    public void setValueSerializer(RedisSerializer valueSerializer) {
        redisTemplate.setValueSerializer(valueSerializer);
    }

    public void setHashValueSerializer(RedisSerializer hashValueSerializer) {
        redisTemplate.setHashValueSerializer(hashValueSerializer);
    }

    /**
     * 设置失效时间
     *
     * @param key     redisKey
     * @param seconds 秒
     */
    public void expire(final String key, final long seconds) {
        if (seconds > 0) {
            redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
        }
    }

    /**
     * 根据key 获取过期时间
     *
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    public long ttl(final String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * @param key      键
     * @param timeUnit 返回时间单位
     * @return
     */
    public long ttl(final String key, final TimeUnit timeUnit) {
        return redisTemplate.getExpire(key, timeUnit);
    }

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 删除缓存
     *
     * @param keys
     */
    public void del(final String... keys) {
        // TODO: 2021/1/10 增加keys删除测试
        Optional.ofNullable(keys).ifPresent(ks -> {
            redisTemplate.delete(Arrays.asList(ks));
        });
    }

    /**
     * 普通缓存放入
     *
     * @param key 键
     * @param v   值
     */
    public <V> void set(final String key, final V v) {
        redisTemplate.opsForValue().set(key, v);
    }

    /**
     * 普通缓存放入并设置时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     */
    public <V> void set(final String key, final V value, final long time) {
        if (time > 0) {
            redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
        } else {
            set(key, value);
        }
    }

    /**
     * 递增
     *
     * @param key   键
     * @param delta 要增加步长
     * @return
     */
    public long incr(final String key, final long delta, final long time) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        long v = redisTemplate.opsForValue().increment(key, delta);
        redisTemplate.expire(key, time, TimeUnit.SECONDS);
        return v;
    }

    /**
     * 递减
     *
     * @param key   键
     * @param delta 要减少的步长
     * @return
     */
    public long decr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, -delta);
    }

    /**
     * @param k   键
     * @param v   值
     * @param <V>
     * @return 是否存在值
     */
    public <V> boolean setNX(final String k, final V v) {

        return redisTemplate.opsForValue().setIfAbsent(k, v);
    }

    /**
     * @param key 键
     * @param <V>
     * @return
     */
    public <V> V get(final String key) {
        if (key == null) {
            return null;
        }

        ValueOperations<String, V> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }

    /**
     * 批量获取值
     *
     * @param keys 批量键
     * @return
     */
    public <V> List<V> mGet(List<String> keys) {
        if (CollectionUtils.isEmpty(keys)) {
            return null;
        }
        ValueOperations<String, V> operations = redisTemplate.opsForValue();
        return operations.multiGet(keys);
    }

    // ===============================hash=================================
    /**
     * HashGet
     *
     * @param key 键 不能为null
     * @param f   hash key 不能为null
     * @return 值
     */
    public <F, V> V hGet(final String key, final F f) {
        HashOperations<String, F, V> operations = redisTemplate.opsForHash();
        return operations.get(key, f);
    }

    /**
     * hash接口批量获取
     *
     * @param k      key
     * @param fields 字段数据
     */
    public <F, V> Map<F, V> hMGet(final String k, final F... fields) {
        final Map<F, V> ret = new HashMap();
        if (ArrayUtils.isEmpty(fields)) {
            return ret;
        }
        HashOperations<String, F, V> hashOperations = redisTemplate.opsForHash();
        List<V> vList = hashOperations.multiGet(k, Arrays.asList(fields));

        for (int i = 0; i < fields.length; i++) {
            if (vList.get(i) != null) {
                ret.put(fields[i], vList.get(i));
            }
        }
        return ret;
    }

    public <F, V> Map<F, V> hGetAll(final String key) {
        HashOperations<String, F, V> operations = redisTemplate.opsForHash();
        return operations.entries(key);
    }


    public <F, V> void hMSet(final String key, final Map<F, V> map) {
        if (map == null || map.size() == 0) {
            return;
        }
        redisTemplate.opsForHash().putAll(key, map);
    }

    /**
     * HashSet 并设置时间
     *
     * @param key  键
     * @param map  对应多个键值
     * @param time 时间(秒)
     * @return true成功 false失败
     */
    public void hMSet(String key, Map<String, Object> map, long time) {
        hMSet(key, map);
        if (time > 0) {
            expire(key, time);
        }
    }

    public <F, V> void hSet(final String key, final F f, final V v) {
        redisTemplate.opsForHash().put(key, f, v);
    }

    public <K, F, V> boolean hSetNX(final String key, final F f, final V v) {
        return redisTemplate.opsForHash().putIfAbsent(key, f, v);
    }


    /**
     * 删除hash表中的值
     *
     * @param key  键 不能为null
     * @param fields 项 可以使多个 不能为null
     */
    public<F> void hDel(String key, F ... fields) {
        redisTemplate.opsForHash().delete(key, fields);
    }

    /**
     * 判断hash表中是否有该项的值
     *
     * @param key  键 不能为null
     * @param f hashKey项 不能为null
     * @return true 存在 false不存在
     */
    public<F> boolean hHasKey(String key, F f) {
        return redisTemplate.opsForHash().hasKey(key, f);
    }

    public <K, F> long hIncrBy(final String key, final F f, final long increment) {
        return redisTemplate.opsForHash().increment(key, f, increment);
    }

    /**
     * hash递减
     *
     * @param key  键
     * @param f 项
     * @param by   要减少记(小于0)
     * @return
     */
    public <F> long hDecr(String key, F f, long by) {
        return redisTemplate.opsForHash().increment(key, f, -by);
    }
    // ===============================set=================================

    /**
     * 根据key获取Set中的所有值
     *
     * @param key 键
     * @return
     */
    public Set<Object> sGet(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 根据value从一个set中查询,是否存在
     *
     * @param key   键
     * @param value 值
     * @return true 存在 false不存在
     */
    public boolean sHasKey(String key, Object value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }

    /**
     * 将数据放入set缓存
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public long sSet(String key, Object... values) {
        return redisTemplate.opsForSet().add(key, values);
    }

    /**
     * 将set数据放入缓存
     *
     * @param key    键
     * @param time   时间(秒)
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public long sSetAndTime(String key, long time, Object... values) {
        Long count = redisTemplate.opsForSet().add(key, values);
        if (time > 0)
            expire(key, time);
        return count;
    }

    /**
     * 获取set缓存的长度
     *
     * @param key 键
     * @return
     */
    public long sGetSetSize(String key) {
        return redisTemplate.opsForSet().size(key);
    }

    /**
     * 移除值为value的
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    public long setRemove(String key, Object... values) {
        Long count = redisTemplate.opsForSet().remove(key, values);
        return count;
    }
    // ===============================list=================================

    /**
     * 获取list缓存的内容
     *
     * @param key   键
     * @param start 开始
     * @param end   结束 0 到 -1代表所有值
     * @return
     */
    public List<Object> lGet(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 获取list缓存的长度
     *
     * @param key 键
     * @return
     */
    public long lGetListSize(String key) {
        return redisTemplate.opsForList().size(key);
    }

    /**
     * 通过索引 获取list中的值
     *
     * @param key   键
     * @param index 索引 index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @return
     */
    public Object lGetIndex(String key, long index) {
        return redisTemplate.opsForList().index(key, index);
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public boolean lSet(String key, Object value) {
        redisTemplate.opsForList().rightPush(key, value);
        return true;
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     */
    public boolean lSet(String key, Object value, long time) {
        redisTemplate.opsForList().rightPush(key, value);
        if (time > 0)
            expire(key, time);
        return true;
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public boolean lSet(String key, List<Object> value) {
        redisTemplate.opsForList().rightPushAll(key, value);
        return true;
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     */
    public boolean lSet(String key, List<Object> value, long time) {
        redisTemplate.opsForList().rightPushAll(key, value);
        if (time > 0)
            expire(key, time);
        return true;
    }

    /**
     * 根据索引修改list中的某条数据
     *
     * @param key   键
     * @param index 索引
     * @param value 值
     * @return
     */
    public boolean lUpdateIndex(String key, long index, Object value) {
        redisTemplate.opsForList().set(key, index, value);
        return true;
    }

    /**
     * 移除N个值为value
     *
     * @param key   键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    public long lRemove(String key, long count, Object value) {
        Long remove = redisTemplate.opsForList().remove(key, count, value);
        return remove;
    }

/**
 * pipeline形式取值
 * <p>
 * <p>
 * <p>
 * 批量查询redis ,list转换成map返回
 *
 * @param keys   业务值 ，redisKey=prefix+keys
 * @param prefix key前缀
 * @date 2017年12月17日 下午4:49:21
 *//*
    public <K, V> List<V> pipelineGets(final List<K> keys, Class<V> v) {
        List<V> list = new ArrayList();
        if (keys == null || keys.size() == 0) {
            return list;
        }

        return redisTemplate.execute(new RedisCallback<List<V>>() {

            public List<V> doInRedis(RedisConnection connection) throws DataAccessException {
                connection.openPipeline();
                for (K key : keys) {
                    connection.get(getKeySerializer().serialize(key));
                }
                List<Object> objectList = connection.closePipeline();
                List<V> vList = new ArrayList<V>();
                for (Object obj : objectList) {
                    byte[] objB = (byte[]) obj;
                    Object o = getValueSerializer().deserialize(objB);
                    vList.add((V) o);
                }
                return vList;
            }
        });

    }


    *//**
 * 批量查询redis ,list转换成map返回
 *
 * @param keys   业务值 ，redisKey=prefix+keys
 * @param prefix key前缀
 *
 * @date 2017年12月17日 下午4:49:21
 *//*
    public <K, V> Map<K, V> multiGet2Map(List<K> keys, String prefix, Class<V> cls) {
        Map<K, V> maps = new HashMap();
        if (CollectionUtils.isEmpty(keys)) {
            return maps;
        }

        List<String> newKeys = new ArrayList<String>(keys.size());
        for (K key : keys) {
            if (key == null) {
                newKeys.add(null);
            }
            if (StringUtils.isBlank(prefix)) {
                newKeys.add(key.toString());
            } else {
                newKeys.add(prefix + key.toString());
            }
        }
        if (newKeys.size() == 0) {
            return maps;
        }
        List<V> list = null;
        if (newKeys.size() < 10) {
            list = gets(newKeys, cls);
        } else {
            list = pipelineGets(newKeys, cls);
        }
        for (int i = 0; i < list.size(); i++) {
            V rv = list.get(i);
            K rk = keys.get(i);
            if (rk != null && rv != null) {
                // JSON序列化
                maps.put(rk, rv);
            }
        }

        return maps;
    }

    */



}
