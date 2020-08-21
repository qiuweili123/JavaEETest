package org.sang;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by sang on 17-1-6.
 */
@Service
public class DemoServiceImpl implements DemoService {
    @Autowired
    PersonRepository personRepository;

    @Resource
    private PersonCacheManager personCacheManager;

    /**
     * 插入 或者更新
     * 插入或更新数据到dataMap中
     * 并且缓存到 guavaDemo中
     * 如果存在了那么更新缓存中的值
     * 其中key 为 #id+dataMap
     */
    @CachePut(value = "people", key = "#person.id")
    @Override
    public Person save(Person person) {
        Person p = personRepository.save(person);
        System.out.println("为id、key为" + p.getId() + "数据做了缓存");
        return p;
    }

    @CacheEvict(value = "people")
    @Override
    public void remove(Long id) {
        System.out.println("删除了id、key为" + id + "的数据缓存");
        personRepository.delete(id);
    }

    /**
     * 查询
     * 如果数据没有缓存,那么从dataMap里面获取,如果缓存了,
     * 那么从guavaDemo里面获取
     * 并且将缓存的数据存入到 guavaDemo里面
     * 其中key 为 #id+dataMap
     */
    //@Cacheable(value = "people", key = "#person.id")
    @Override
    public Person findOne(Person person) {
        System.out.println("从db中获取数据");
        Person p = personCacheManager.findOneById(person);
        System.out.println("为id、key为" + p.getId() + "数据做了缓存");
        return p;
    }


}
