package com.xiaowushi.skeleton.api.user.model;

import jakarta.validation.constraints.NotBlank;

public class CreateUserReq {
    @NotBlank(message = "username cannot be blank")
    private String username;

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
}