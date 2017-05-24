package com.jilong.search.controller;

import com.jilong.search.domain.user.UserRegister;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jilong.qiu
 * @date 2017/5/9.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/register")
    public Object register(UserRegister user) {
        return null;
    }

}
