package com.huangyuqiang.learn.springdataelasticsearch;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        user.setResume("a long long long long resume.");

        userRepository.save(user);

        User newUser = userRepository.findOne("name");
        assertEquals(user.getName(), newUser.getName());
        assertEquals(user.getAge(), newUser.getAge());
        assertEquals(user.getResume(), newUser.getResume());
        assertEquals(1, userRepository.count());
    }

    @Test
    public void search() {
        assertEquals(0, userRepository.count());

        User user = new User();
        user.setName("name");
        user.setAge(20);
        user.setResume("a long long long long resume.");
        userRepository.save(user);

        List<User> users1 = userRepository.findByResumeContains("resume");
        List<User> users2 = userRepository.findByResumeContains("nothing");

        assertEquals(1, users1.size());
        assertEquals(0, users2.size());
    }
}
