package com.demo.service;

import com.example.model.Users;

import java.util.List;

public interface IUserService {

    int addUser(Users users);
     List<Users> findUser();
}
