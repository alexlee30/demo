package com.example.demo.controller;

import java.util.List;

import com.example.demo.entity.PageSet;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(value = "/AddressList", method = { RequestMethod.GET, RequestMethod.POST })
public class AddressList {

    @Autowired
    private UserService userservice;

    @RequestMapping("/ListUser")
    @ResponseBody
    public List<User> ListUser(){
        return userservice.ListUser();
    }

    @RequestMapping("/ListUserByname")
    @ResponseBody
    public List<User> ListUserByname(String name){
        return userservice.findByName(name);
    }


    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(int id) {
        int result = userservice.delete(id);
        if (result >= 1) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(User user) {
        int result = userservice.Update(user);
        if (result >= 1) {
            return "修改成功";
        } else {
            return "修改失败";
        }

    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public User insert(User user) {
        return userservice.insertUser(user);
    }

//    @RequestMapping(value = "/getPageListDesc")
//    @ResponseBody
//    public List<User> getPageListDesc(PageSet pageSet){
//        return userservice.getPageList(pageSet);
//    }

    @RequestMapping(value = "/getPageListDesc")
    @ResponseBody
    public String getPageListDesc(PageSet pageSet){
        int total = countAll();
        return userservice.getPageList(pageSet) + "<br>总数:" + total;
    }

    @RequestMapping(value = "/getPageListAsc")
    @ResponseBody
    public String getPageListAsc(PageSet pageSet){
        int total = countAll();
        return userservice.getPageListAsc(pageSet) + "<br>总数：" + total;
    }

//    @RequestMapping(value = "/getPageListAsc")
//    @ResponseBody
//    public List<User> getPageListAsc(PageSet pageSet){
//        return userservice.getPageListAsc(pageSet);
//    }

    @RequestMapping(value = "/countAll", method = RequestMethod.GET)
    public int countAll(){
        return userservice.countAll();
    }
}