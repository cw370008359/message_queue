package com.danhesoft;

import com.danhesoft.config.MvcConfiguration;
import com.danhesoft.controller.PersonController;
import com.danhesoft.controller.UserController;
import javafx.application.Application;
import org.apache.tomcat.jni.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by caowei on 2018/4/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = HamcrestTest.class)
@ContextConfiguration(classes = MvcConfiguration.class)
public class HamcrestTest {

    private MockMvc mvc;
    private MockMvc userMvc;

    @Before
    public void setUp(){
        mvc = MockMvcBuilders.standaloneSetup(new PersonController()).build();
        userMvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
    }

    @Test
    public void test()throws Exception{
        if(mvc!=null){
            MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/person/getInfo").accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();
            System.out.println(mvcResult.getResponse().getContentAsString());
            /*.andExpect(status().isOk()).
                    andExpect(content().string("曹伟1"));*/
        }
    }

    @Test
    public void testGetUserInfo(){
        if(userMvc!=null){
            try {
                MvcResult mvcResult = userMvc.perform(MockMvcRequestBuilders.get("/user/getInfo").accept(MediaType.APPLICATION_JSON)).andReturn();
                System.out.println(mvcResult.getResponse().getContentAsString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



}
