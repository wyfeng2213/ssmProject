package com.example.dao;

import com.example.model.Img;
import org.springframework.stereotype.Repository;

/**
 * ImgDAO继承基类
 */
@Repository
public interface ImgDAO extends MyBatisBaseDao<Img, Integer> {
}