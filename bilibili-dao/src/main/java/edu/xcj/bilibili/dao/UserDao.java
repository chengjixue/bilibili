package edu.xcj.bilibili.dao;

import edu.xcj.bilibili.domain.User;
import edu.xcj.bilibili.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author xuecj
 * @version 1.0
 * @date 2022/5/24 12:03
 */
@Mapper
public interface UserDao {


    User getUserByPhone(String phone);

    Integer addUser(User user);

    Integer addUserInfo(UserInfo userInfo);

    User getUserById(Long id);

    UserInfo getUserInfoByUserId(Long userId);
}