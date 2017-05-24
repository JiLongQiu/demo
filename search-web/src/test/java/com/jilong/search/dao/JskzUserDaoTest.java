package com.jilong.search.dao;

import com.jilong.search.domain.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author jilong.qiu
 * @date 2017/5/16.
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-common.xml"})
public class JskzUserDaoTest {

    @Resource
    private JskzUserDao dao;

    @Test
    public void init() {
        User user = dao.get(1980);
        System.out.println(user);
    }

}