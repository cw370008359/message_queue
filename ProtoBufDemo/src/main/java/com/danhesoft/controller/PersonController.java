package com.danhesoft.controller;

import com.danhesoft.FirstProtobuf;
import com.danhesoft.util.ByteToEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by caowei on 2018/4/19.
 */
@RestController
@RequestMapping("/person")
public class PersonController {

    @RequestMapping("/getInfo")
    public String getUserInfo(){
        FirstProtobuf.Person.Builder personBuild = FirstProtobuf.Person.newBuilder();
        personBuild.setPersonName("曹伟1");
        personBuild.setPersonAge(32);
        personBuild.setPersonSex("男");
        personBuild.addFriends("周永灿");
        personBuild.addFriends("金浩");

        FirstProtobuf.Person person = personBuild.build();
        byte[] person_serial = person.toByteArray();

        return new ByteToEntity().getUserName(person_serial);
    }



}
