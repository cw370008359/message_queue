package com.danhesoft.controller;

import com.danhesoft.domain.UserDomain;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by caowei on 2018/4/20.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/getInfo")
    public UserDomain getUserInfo(){
        UserDomain userDomain = new UserDomain();
        userDomain.setUserId("caowei");
        userDomain.setUserName("曹伟");
        userDomain.setUserAge(32);
        return userDomain;
    }
}
