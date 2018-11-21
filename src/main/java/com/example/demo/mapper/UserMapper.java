package com.example.demo.mapper;

import java.util.List;

import com.example.demo.entity.PageSet;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    List<User> findUserByName(String name);

    public List<User> ListUser();

    public int insertUser(User user);

    public int delete(int id);

    public int Update(User user);

    public List<User> getPageList(PageSet pageSet);

    public List<User> getPageListAsc(PageSet pageSet);

    public int countAll();
}