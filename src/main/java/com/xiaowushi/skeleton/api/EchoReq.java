package com.xiaowushi.skeleton.api;

import jakarta.validation.constraints.NotBlank;

public class EchoReq {

    @NotBlank(message = "msg cannot be blank")
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}