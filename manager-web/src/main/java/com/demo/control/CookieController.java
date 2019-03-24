package com.demo.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CookieController {

    @RequestMapping("/cookie")
    public String goIndex(HttpServletResponse response) {
        Cookie c = new Cookie("key", "value11");
//        c.setMaxAge(10);
//        c.setPath("/page");
//3. 域名和当前项目的域名相同
//        c.setDomain(".bjsxt.com");
        response.addCookie(c);
        return "redirect:/index.jsp";
    }

}
