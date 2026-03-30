package com.xiaowushi.skeleton.api.security;

import com.xiaowushi.skeleton.api.exception.UnauthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JwtAuthInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(JwtAuthInterceptor.class);

    private final JwtUtil jwtUtil;

    public JwtAuthInterceptor(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String authHeader = request.getHeader("Authorization");
        log.info("Authorization header={}", authHeader);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            log.warn("JWT 校验失败：缺少 Bearer token");
            throw new UnauthorizedException("未登录，请携带 Bearer token");
        }

        String token = authHeader.substring(7);
        try {
            Claims claims = jwtUtil.parseToken(token);
            Long userId = Long.valueOf(claims.getSubject());
            String username = (String) claims.get("username");

            UserContext.set(userId, username);
            log.info("JWT 校验成功：userId={}, username={}", userId, username);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            log.warn("JWT 校验失败：{}", e.getMessage());
            throw new UnauthorizedException("token 非法或已过期");
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        UserContext.clear();
    }
}