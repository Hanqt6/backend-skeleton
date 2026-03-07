package com.xiaowushi.skeleton.api.user.service;

import com.xiaowushi.skeleton.api.BizException;
import com.xiaowushi.skeleton.api.user.mapper.UserMapper;
import com.xiaowushi.skeleton.api.user.model.User;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Transactional
    public User create(String username) {
        User u = new User();
        u.setUsername(username);
        try {
            userMapper.insert(u);
            return u;
        } catch (DuplicateKeyException e) {
            throw new BizException(400, "username already exists");
        }
    }

    public User getById(Long id) {
        User u = userMapper.findById(id);
        if (u == null) throw new BizException(404, "user not found");
        return u;
    }
}