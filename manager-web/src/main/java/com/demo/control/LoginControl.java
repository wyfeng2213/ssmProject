package com.demo.control;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Auther: wangyong
 * @Date: 2019/7/3
 * @Description:
 */
@Controller
public class LoginControl {
    //1.登录处理
    @RequestMapping(value = "/login",produces ="text/html; charset=utf-8")
    @ResponseBody
    public String login(HttpServletRequest req, Model model) {
        System.out.println("执行了login control");
        //下面是有异常才会走下面的代码 , 成功会走配置文件的登录成功请求
        String exceptionClassName = (String) req.getAttribute("shiroLoginFailure");
        String error = "认证失败";
        if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if (exceptionClassName != null) {
            error = "其他错误：" + exceptionClassName;
        }

//        model.addAttribute("error", error);
//        return "redirect:login.jsp";
        return error;
    }

    //2.去登录页面
    @RequestMapping(value =  "/toLogin",produces ="text/html; charset=utf-8")
    @ResponseBody
    public String toLogin() {
        return "退出登录成功";
    }


    //退出登录
    @RequestMapping(value = "/logout",produces ="text/html; charset=utf-8")
    @ResponseBody
    public String logout() {
        System.out.println("=======退出登录==========");
        return "退出登录成功";
    }

//    //3.首页处理
//    @RequestMapping(value = {"/index"})
//    public ModelAndView index(HttpSession session) {
//        Subject subject = SecurityUtils.getSubject();
//        BIConversion.User user = (BIConversion.User) subject.getPrincipal();
//        session.setAttribute("currentUser", user);
//        return new ModelAndView("index");
//    }

    //3.认证成功
    @RequestMapping(value = {"/index"},produces ="text/html; charset=utf-8")
    @ResponseBody
    public String index(HttpSession session) {
//        Subject subject = SecurityUtils.getSubject();
//        BIConversion.User user = (BIConversion.User) subject.getPrincipal();
//        session.setAttribute("currentUser", user);
//        return new ModelAndView("index");
        return "认证成功";
    }
}
