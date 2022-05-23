package edu.xcj.bilibili.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * @author xuecj
 * @version 1.0
 * @date 2022/5/22 23:33
 */
@Mapper
public interface DemoDao {

    public Long query(Long id);
}
