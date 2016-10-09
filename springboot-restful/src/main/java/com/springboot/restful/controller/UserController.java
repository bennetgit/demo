package com.springboot.restful.controller;

import com.springboot.restful.controller.response.ResponseInfo;
import com.springboot.restful.domain.User;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by feng on 16/9/19.
 */

@RestController
@RequestMapping("/users")
public class UserController {


    static Map<Long, User> users = new ConcurrentHashMap<Long, User>();

    @ApiOperation(value = "获取用户信息")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<User> getUsers() {
        return new ArrayList<User>(users.values());
    }

    @ApiOperation(value = "插入用户信息",notes = "插入用户信息")
    @ApiImplicitParam(name = "user",value = "用户实体user", dataType = "User")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseInfo<String> insert(@ModelAttribute User user) {
        users.put(user.getId(), user);
        return new ResponseInfo<String>(200, "success");
    }

    @ApiOperation(value = "查询指定用户信息",notes = "根据用户id查询用户信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseInfo<User> getUser(@PathVariable Long id) {
        return new ResponseInfo<User>(200, "success", users.get(id));
    }


    @ApiOperation(value = "更新用户信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseInfo<String> updateUser(@PathVariable Long id, @ModelAttribute User user) {
        users.put(id, user);
        return new ResponseInfo<String>(200, "success");
    }

    @ApiOperation(value = "删除用户信息")
    @ApiImplicitParam(name = "id",value = "用户id", dataType = "Long")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseInfo<String> deleteUser(@PathVariable Long id) {
        users.remove(id);
        return new ResponseInfo<String>(200, "success");
    }
}
