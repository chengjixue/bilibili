package edu.xcj.bilibili.service;

import edu.xcj.bilibili.dao.DemoDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xuecj
 * @version 1.0
 * @date 2022/5/22 23:58
 */
@Service
public class DemoService {
    @Resource
    DemoDao demoDao;
    public Long qurey(Long id){
        return demoDao.query(id);
    }
}
