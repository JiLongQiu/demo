package com.jilong.search.dao;

import com.jilong.search.domain.user.User;
import com.jilong.search.domain.user.UserCondition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author jilong.qiu
 * @date 2017/5/2.
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-common.xml"})
public class UserEsDaoTest {

    @Autowired
    private UserEsDao userEsDao;

    @Test
    public void testQuery() throws Exception {
        UserCondition cd = new UserCondition();
        cd.setName("北京大饭店");
        List<User> query = userEsDao.query(cd);
    }

    @Test
    public void testInsert() throws Exception {
        User user = new User();
        user.setAge(20);
        user.setId(2);
        user.setName("这是北京哈你懂得大饭店");
        userEsDao.insert(user);
    }
}