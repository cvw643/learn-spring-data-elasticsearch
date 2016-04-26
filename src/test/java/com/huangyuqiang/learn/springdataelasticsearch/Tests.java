package com.huangyuqiang.learn.springdataelasticsearch;

import com.huangyuqiang.learn.springdataelasticsearch.domain.Address;
import com.huangyuqiang.learn.springdataelasticsearch.domain.User;
import com.huangyuqiang.learn.springdataelasticsearch.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by huangyuqiang on 2016/4/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class Tests {
    @Autowired
    UserRepository userRepository;

    @Before
    public void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    public void index() {
        assertEquals(0, userRepository.count());

        User user = new User();
        user.setName("name");
        user.setAge(20);
        user.setResume("resume.");
        Address address = new Address();
        address.setCity("北京");
        address.setCode(100001);
        address.setStreet("长安街1号");
        user.setAddress(address);
        userRepository.save(user);

        User newUser = userRepository.findOne("name");
        assertEquals(user.getName(), newUser.getName());
        assertEquals(user.getAge(), newUser.getAge());
        assertEquals(user.getResume(), newUser.getResume());
        assertEquals(user.getAddress().getCode(), newUser.getAddress().getCode());
        assertEquals(user.getAddress().getCity(), newUser.getAddress().getCity());
        assertEquals(user.getAddress().getStreet(), newUser.getAddress().getStreet());
        assertEquals(1, userRepository.count());
    }

    @Test
    public void search() {
        assertEquals(0, userRepository.count());

        User user = new User();
        user.setName("name");
        user.setAge(20);
        user.setResume("resume.");
        Address address = new Address();
        address.setCity("北京");
        address.setCode(100001);
        address.setStreet("长安街1号");
        user.setAddress(address);
        userRepository.save(user);

        List<User> users1 = userRepository.findByResumeContains("resume");
        List<User> users2 = userRepository.findByAddressStreetContains("长安");

        assertEquals(1, users1.size());
        assertEquals(1, users2.size());
    }
}
