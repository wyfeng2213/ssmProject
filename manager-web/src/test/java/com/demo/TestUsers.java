package com.demo;


import com.example.dao.UsersDAO;
import com.example.model.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

//@RunWith(SpringJUnit4ClassRunner.class)
//// 告诉junit spring配置文件
//@ContextConfiguration({"classpath:spring/applicationContext-service.xml", "classpath:spring/applicationContext-trans.xml"
//        , "classpath:spring/applicationContext-dao.xml"
//})

public class TestUsers {
    @Resource
    UsersDAO usersDAO;
    @Test
    public void  insertUserTest(){
//        Users users= new Users();
//        users.setUsername("张三");
//        users.setUserage(12);
//
//        usersDAO.insertSelective(users);
    }
}
