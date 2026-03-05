package com.xiaowushi.skeleton.api;

public class ApiResponse<T> {
    private int code;      // 0=success, others=error
    private String msg;
    private T data;

    public ApiResponse() {}

    public ApiResponse(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(0, "ok", data);
    }

    public static <T> ApiResponse<T> fail(int code, String msg) {
        return new ApiResponse<>(code, msg, null);
    }

    public int getCode() { return code; }
    public String getMsg() { return msg; }
    public T getData() { return data; }

    public void setCode(int code) { this.code = code; }
    public void setMsg(String msg) { this.msg = msg; }
    public void setData(T data) { this.data = data; }
}