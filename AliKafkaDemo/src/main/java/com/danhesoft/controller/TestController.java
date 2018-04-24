package com.danhesoft.controller;

import com.danhesoft.kafka.consumer.ConsumerUtil;
import com.danhesoft.kafka.producer.ProducerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

/**
 * Created by caowei on 2018/4/23.
 */
@RestController
public class TestController {

    @Autowired
    private ProducerUtil producerUtil;

    @Autowired
    private ConsumerUtil consumerUtil;

    @RequestMapping("/test")
    public String testProducer(String messages){
        producerUtil.produceMsg(messages);
        return "test produce complete";
    }
/*
    @RequestMapping("/consumer")
    public String testConsumer(){
        consumerUtil.doService();
        return "test consumer complete";
    }*/

}
