package com.xiaowushi.skeleton.api.controller;

import com.xiaowushi.skeleton.api.ApiResponse;
import com.xiaowushi.skeleton.api.security.UserContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SecureController {

    @GetMapping("/secure/ping")
    public ApiResponse<Map<String, Object>> securePing() {
        Map<String, Object> data = new HashMap<>();
        data.put("message", "secure pong");
        data.put("userId", UserContext.getUserId());
        data.put("username", UserContext.getUsername());
        return ApiResponse.ok(data);
    }
}