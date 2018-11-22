package com.example.demo.controller;

import java.util.List;

import com.example.demo.entity.PageSet;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping(value = "/AddressList")
@Api("swagger AddressListController相关的api")
public class AddressListController {

    @Autowired
    private UserService userservice;

    private final static Logger logger = LoggerFactory.getLogger(AddressListController.class);

    @ApiOperation(value = "获取所有用户信息", notes = "获取所有用户信息")
    @GetMapping(value = "/ListUser" )
    @ResponseBody
    public List<User> ListUser(){
        logger.info("获取所有用户");
        return userservice.ListUser();
    }

    @ApiOperation(value="删除用户", notes="删除用户")
    @ApiParam(name = "id", value = "用户id", required = true)
    @PostMapping(value = "/delete", produces = {"text/plain;charset=UTF-8"})
    public String delete(@RequestParam("id")  int id) {
        logger.info("删除用户，用户id{}", id);
        int result = userservice.delete(id);
        if (result >= 1) {
            return "删除成功";
        } else {
            logger.warn("删除失败");
            return "删除失败";
        }
    }

    @ApiOperation(value="修改用户", notes="修改用户")
    @ApiImplicitParam(name = "user", value = "用户数据模型", required = true, dataType = "User")
    @PostMapping(value = "/update",  produces = {"text/plain;charset=UTF-8"})
    public String update(@RequestBody  User user) {
        logger.info("修改用户{}",user.getName());
        int result = userservice.Update(user);
        if (result >= 1) {
            return "修改成功";
        } else {
            return "修改失败";
        }

    }

    @ApiOperation(value="新增用户", notes="新增用户")
    @ApiImplicitParam(name = "user", value = "用户数据模型", required = true, dataType = "User")
    @PostMapping(value = "/insert", produces = {"text/plain;charset=UTF-8"})
    public String insert(@RequestBody User user) {
        logger.info("新增用户{}",user.getName());
        int result = userservice.insertUser(user);
        if (result >= 1) {
            return "修改成功";
        } else {
            return "修改失败";
        }
    }

    @ApiOperation(value="获取降序分页数据", notes="获取降序分页数据")
    @ApiImplicitParam(name = "pageSet", value = "分页数据模型", required = true, dataType = "PageSet")
    @RequestMapping(value = "/getPageListDesc", method = RequestMethod.GET)
    @ResponseBody
    public String getPageListDesc(@ModelAttribute PageSet pageSet){
        logger.info("获取降序分页数据");
        int total = countAll();
        return userservice.getPageList(pageSet) + "<br>总数:" + total;
    }

    @ApiOperation(value="获取分页数据", notes="获取分页数据")
    @ApiImplicitParam(name = "pageSet", value = "分页数据模型", required = true, dataType = "PageSet")
    @RequestMapping(value = "/getPageListAsc", method = RequestMethod.GET)
    @ResponseBody
    public String getPageListAsc(@ModelAttribute PageSet pageSet){
        logger.debug("获取分页数据");
        int total = countAll();
        return userservice.getPageListAsc(pageSet) + "<br>总数：" + total;
    }

    @RequestMapping(value = "/countAll", method = RequestMethod.GET)
    public int countAll(){
        return userservice.countAll();
    }
}