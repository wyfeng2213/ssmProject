package com.demo.service;

import com.example.dao.UsersDAO;
import com.example.model.Users;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Resource
    UsersDAO usersDAO;

    public int addUser(Users users) {

        return usersDAO.insertSelective(users);
    }

    public List<Users> findUser() {
        PageHelper.startPage(1, 3);
        List<Users> list = usersDAO.selectAll();
        PageInfo pageInfo = new PageInfo(list);
        System.out.println(pageInfo.toString());
        return list;
    }
}
