package com.ws.controller;

import com.ws.model.User;
import com.ws.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    @ResponseBody
    public User one(@PathVariable("id") String id){
        User user = userService.selectOne(id);
        return user;
    }

    @PostMapping
    @ResponseBody
    public User newOne(User user){
        userService.newOne(user);
        return user;
    }


}
