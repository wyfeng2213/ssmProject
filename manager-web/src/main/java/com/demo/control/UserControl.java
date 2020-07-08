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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@RequestMapping("/user")
public class UserControl {
    @Resource
    IUserService userServiceImpl;

    @RequestMapping("/addUser")
    public String addUser() {
        System.out.println("11111111111");
        System.out.println("22222222222");
        System.out.println("33333333333");
        Users users = new Users();
        users.setUsername("lisi");
        users.setUserage(25);
        userServiceImpl.addUser(users);
        return "ok";
    }


    @RequestMapping("/findUser")
    @ResponseBody
    public List<Users> findUser() {
        List<Users> list = userServiceImpl.findUser();
        return list;
    }

    //跳转到登陆界面
    @RequestMapping("/showLogin")
    public String Login() {
        return "login";
    }

    @RequestMapping("/showRegister")
    public String showRegister() {
        return "register";
    }

    @Resource
    private JedisCluster jedisClients;

    //登陆流程 包括缓存
    @RequestMapping("/login")
    @ResponseBody
    public EgoResult login(TbUser user, HttpServletRequest request, HttpServletResponse response) {
        EgoResult r = new EgoResult();
        if (user.getUsername().equals("zs") && user.getPassword().equals("123456")) {
            String key = UUID.randomUUID().toString();
            jedisClients.set(key, JSON.toJSONString(user));
            jedisClients.expire(key, 60 * 60 * 24 * 7);
            //产生 Cookie
            CookieUtils.setCookie(request, response,
                    "TT_TOKEN", key, 60 * 60 * 24 * 7);
            r.setMsg("成功");
            r.setData("成功");
            r.setStatus(200);
        } else {
            r.setMsg("失败");
            r.setData("失败");
            r.setStatus(201);
        }
        return r;
    }

    //根据token取用户信息
    @RequestMapping("/token/{token}")
    @ResponseBody
    public Object getUserInfo(@PathVariable String token) {
        EgoResult er = new EgoResult();
        String json = jedisClients.get(token);
        if (json != null && !json.equals("")) {
            TbUser tbUser = JSON.parseObject(json, TbUser.class);
            //可以把密码清空
            tbUser.setPassword(null);
            er.setStatus(200);
            er.setMsg("OK");
            er.setData(tbUser);
        } else {
            er.setMsg("获取失败");
        }
        return er;
    }

    //退出
    @RequestMapping("/logout/{token}")
    @ResponseBody
    public Object logout(@PathVariable String
                                 token, String callback, HttpServletRequest
                                 request, HttpServletResponse response) {
        Long index = jedisClients.del(token);
        CookieUtils.deleteCookie(request, response,
                "TT_TOKEN");
        EgoResult er = new EgoResult();
        er.setStatus(200);
        er.setMsg("OK");
        return er;
    }
}
