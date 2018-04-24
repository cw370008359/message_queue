package com.danhesoft.util;

import com.danhesoft.FirstProtobuf;
import com.google.protobuf.InvalidProtocolBufferException;

import java.io.ByteArrayInputStream;
import java.util.List;

/**
 * Created by caowei on 2018/4/19.
 */
public class ByteToEntity {

    public String getUserName(byte[] bytes){
        String userName = "";
        //反序列化
        try {
            FirstProtobuf.Person newPerson = FirstProtobuf.Person.parseFrom(bytes);
            if(newPerson!=null){
                System.out.println(newPerson.getPersonName());
                System.out.println(newPerson.getPersonAge());
                System.out.println(newPerson.getPersonSex());
                userName = newPerson.getPersonName();
                List<String> list = newPerson.getFriendsList();
                for(String friend: list){
                    System.out.println(friend);
                }

            }
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        return userName;
    }

}
