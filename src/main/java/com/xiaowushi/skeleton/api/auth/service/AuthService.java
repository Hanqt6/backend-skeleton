package com.xiaowushi.skeleton.api.auth.service;

import com.xiaowushi.skeleton.api.mapper.UserMapper;
import com.xiaowushi.skeleton.api.security.JwtUtil;
import com.xiaowushi.skeleton.api.user.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    private final UserMapper userMapper;
    private final JwtUtil jwtUtil;

    public AuthService(UserMapper userMapper, JwtUtil jwtUtil) {
        this.userMapper = userMapper;
        this.jwtUtil = jwtUtil;
    }

    public Map<String, Object> login(String username) {
        User user = userMapper.findByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("用户不存在");
        }

        String token = jwtUtil.generateToken(user.getId(), user.getUsername());

        Map<String, Object> data = new HashMap<>();
        data.put("token", token);
        data.put("tokenType", "Bearer");
        data.put("userId", user.getId());
        data.put("username", user.getUsername());
        data.put("expireSeconds", 3600);
        return data;
    }
}