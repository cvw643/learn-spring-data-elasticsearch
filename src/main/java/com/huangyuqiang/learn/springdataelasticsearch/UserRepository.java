package com.huangyuqiang.learn.springdataelasticsearch;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by huangyuqiang on 2016/4/25.
 */
@Repository
public interface UserRepository extends PagingAndSortingRepository<User, String> {
    List<User> findByResumeContains(String keyword);
}
