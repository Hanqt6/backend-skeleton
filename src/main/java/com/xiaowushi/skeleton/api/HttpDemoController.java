package com.xiaowushi.skeleton.api;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Validated
public class HttpDemoController {

    // Query 参数示例
    @GetMapping("/echo")
    public ApiResponse<Map<String, Object>> echoQuery(
            @RequestParam @NotBlank(message = "msg is required") String msg
    ) {
        return ApiResponse.ok(Map.of("msg", msg));
    }

    // JSON Body 示例
    @PostMapping("/echo")
    public ApiResponse<Map<String, Object>> echoBody(@Valid @RequestBody EchoReq req) {
        return ApiResponse.ok(Map.of("msg", req.getMsg()));
    }

    // Header 示例：伪鉴权
    @GetMapping("/secure/demo")
    public ApiResponse<Map<String, Object>> securePing(
            @RequestHeader(value = "Authorization", required = false) String auth
    ) {
        if (auth == null || !auth.startsWith("Bearer ")) {
            throw BizException.unauthorized("missing or invalid token");
        }
        return ApiResponse.ok(Map.of("securePong", true));
    }
}