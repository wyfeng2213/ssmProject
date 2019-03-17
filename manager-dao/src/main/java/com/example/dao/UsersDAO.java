package com.example.dao;


import com.example.model.Users;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UsersDAO继承基类
 */
@Repository
public interface UsersDAO extends MyBatisBaseDao<Users, Integer> {
    List<Users> selectAll();
}