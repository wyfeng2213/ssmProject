package com.demo.control;

import com.alibaba.fastjson.JSON;
import com.demo.service.IUserService;
import com.demo.util.BaseResult;
import com.demo.util.CookieUtils;
import com.demo.util.EgoResult;
import com.demo.util.TbUser;
import com.example.model.Users;
import com.fasterxml.jackson.annotation.JsonAlias;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

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

    @RequestMapping("/showLogin")
    public String Login(){
        return "login";
    }

    @RequestMapping("/showRegister")
    public String showRegister(){
        return "register";
    }

    @Resource
    private JedisCluster jedisClients;
    @RequestMapping("/login")
    @ResponseBody
    public EgoResult login(TbUser user, HttpServletRequest request, HttpServletResponse response){
        EgoResult r=new EgoResult();
        if(user.getUsername().equals("zs")&&user.getPassword().equals("123456")){
            String key = UUID.randomUUID().toString();
            jedisClients.set(key, JSON.toJSONString(user));
            jedisClients.expire(key,60*60*24*7);
            //产生 Cookie
            CookieUtils.setCookie(request, response,
                    "TT_TOKEN", key, 60*60*24*7);
            r.setMsg("成功");
            r.setData("成功");
            r.setStatus(200);
        }  else {
            r.setMsg("失败");
            r.setData("失败");
            r.setStatus(201);
        }
        return r;
    }
}
