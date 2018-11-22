package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.User;
import com.example.demo.entity.PageSet;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserService {
    @Autowired
    public UserMapper userMapper;

    public List<User> findByName(String name) {
        return userMapper.findUserByName(name);
    }

    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }
    public List<User> ListUser(){
        return userMapper.ListUser();
    }


    public int Update(User user){
        return userMapper.Update(user);
    }

    public int delete(int id){
        return userMapper.delete(id);
    }

    public List<User> getPageList(PageSet pageSet){
        return userMapper.getPageList(pageSet);
    }

    public List<User> getPageListAsc(PageSet pageSet){
        return  userMapper.getPageListAsc(pageSet);
    }

    public int countAll(){
        return userMapper.countAll();
    }
}