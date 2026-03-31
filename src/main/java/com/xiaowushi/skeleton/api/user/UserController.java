package com.xiaowushi.skeleton.api.user;

import com.xiaowushi.skeleton.api.ApiResponse;
import com.xiaowushi.skeleton.api.user.model.CreateUserReq;
import com.xiaowushi.skeleton.api.user.model.User;
import com.xiaowushi.skeleton.api.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

@RestController
@Validated
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ApiResponse<User> create(@Valid @RequestBody CreateUserReq req) {
        return ApiResponse.ok(userService.create(req.getUsername()));
    }

    @GetMapping("/{id}")
    public ApiResponse<User> get(@PathVariable Long id) {
        return ApiResponse.ok(userService.getById(id));
    }

    @PostMapping("/idempotent")
    public ApiResponse<User> createIdempotent(@RequestParam @NotBlank String username) {
        return ApiResponse.ok(userService.createOrGetByUsername(username));
    }
}