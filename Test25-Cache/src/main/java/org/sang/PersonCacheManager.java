package org.sang;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class PersonCacheManager {
    @Autowired
    PersonRepository personRepository;

    @Cacheable(value = "people", key = "#person.id")
    public Person findOneById(Person person) {
        System.out.println("从id中获取数据11111111111");
        Person p = personRepository.findOne(person.getId());
        System.out.println("为id、key为" + p.getId() + "数据做了缓存");
        return p;
    }
}
