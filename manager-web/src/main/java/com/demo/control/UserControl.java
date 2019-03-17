package com.demo.control;

import com.demo.service.IUserService;
import com.example.model.Users;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserControl {
    @Resource
    IUserService userServiceImpl;
    @RequestMapping("/addUser")
    public String addUser() {
        Users users = new Users();
        users.setUsername("lisi");
        users.setUserage(25);
        userServiceImpl.addUser(users);
        return "ok";
    }


    @RequestMapping("/findUser")
    @ResponseBody
    public List<Users> findUser(){
        List<Users> list = userServiceImpl.findUser();
        return list;
    }
}
