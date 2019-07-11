package com.ws.service;

import com.ws.dao.UserMapper;
import com.ws.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User selectOne(String id) {
        return userMapper.selectOne(id);
    }

    @Transactional()
    public void newOne(User user) {
        userMapper.addUser(user);
    }

}
