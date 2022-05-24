package edu.xcj.bilibili.service;

import com.mysql.cj.util.StringUtils;
import edu.xcj.bilibili.dao.UserDao;
import edu.xcj.bilibili.domain.User;
import edu.xcj.bilibili.domain.UserInfo;
import edu.xcj.bilibili.domain.constant.UserConstant;
import edu.xcj.bilibili.domain.exception.ConditionException;
import edu.xcj.bilibili.service.util.MD5Util;
import edu.xcj.bilibili.service.util.RSAUtil;
import edu.xcj.bilibili.service.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import sun.security.provider.MD5;

import java.util.Date;

/**
 * @author xuecj
 * @version 1.0
 * @date 2022/5/24 12:00
 */
@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public void addUser(User user) {
        String phone = user.getPhone();
        if (StringUtils.isNullOrEmpty(phone)) {
            throw new ConditionException("手机号不能为空");
        }
        User dbUser = this.getUserByPhone(phone);
        if (dbUser != null) {
            throw new ConditionException("该手机号已经注册");
        }
        //获取当前时间给MD5加密当盐值
        Date now = new Date();
        String salt = String.valueOf(now.getTime());
        //经过RSA加密出传过来的密码
        String password = user.getPassword();
        String rawPassword;
        try {
            rawPassword = RSAUtil.decrypt(password);
        } catch (Exception e) {
            throw new ConditionException("解密失败!");
        }
        MD5Util.sign(rawPassword, salt, "UTF-8");
        user.setSalt(salt);
        user.setPassword(password);
        user.setCreateTime(now);
        userDao.addUser(user);
        //    添加用户信息
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(user.getId());
        userInfo.setNick(UserConstant.DEFAULT_NICK);
        userInfo.setBirth(UserConstant.DEFAULT_BIRTH);
        userInfo.setGender(UserConstant.GENDER_MALE);
        userInfo.setCreateTime(now);
        userDao.addUserInfo(userInfo);
    }

    public User getUserByPhone(String phone) {
        return userDao.getUserByPhone(phone);
    }
    /*
    *
    * 登录
    * */
    public String login(User user) {
        String phone = user.getPhone();
        if (StringUtils.isNullOrEmpty(phone)) {
            throw new ConditionException("手机号不能为空");
        }
        User dbUser = this.getUserByPhone(phone);
        if (dbUser == null) {
            throw new ConditionException("当前用户不存在");
        }
        String password = user.getPassword();
        String rawPassword;
        try {
            rawPassword= RSAUtil.decrypt(password);
        } catch (Exception e) {
            throw new ConditionException("解密失败!");
        }
        String salt=dbUser.getSalt();
        String md5Password = MD5Util.sign(rawPassword, salt, "UTF-8");
        if (md5Password.equals(dbUser.getPassword())) {
            throw new ConditionException("密码错误!");
        }
    //    生成用户令牌
        TokenUtil tokenUtil = new TokenUtil();
        return tokenUtil.generateToken(dbUser.getId());
    }
}
