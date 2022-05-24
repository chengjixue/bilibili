package edu.xcj.bilibili.service;

import edu.xcj.bilibili.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xuecj
 * @version 1.0
 * @date 2022/5/24 12:00
 */
@Service
public class UserService {
    @Autowired
    UserDao userDao;


}
