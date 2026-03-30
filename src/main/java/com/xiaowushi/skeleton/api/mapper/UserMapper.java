package com.xiaowushi.skeleton.api.mapper;

import com.xiaowushi.skeleton.api.user.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Insert("""
        INSERT INTO `user`(username)
        VALUES(#{username})
    """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(User user);

    @Select("""
        SELECT id, username, created_at
        FROM `user`
        WHERE id = #{id}
    """)
    User findById(@Param("id") Long id);

    @Select("""
        SELECT id, username, created_at
        FROM `user`
        WHERE username = #{username}
    """)
    User findByUsername(@Param("username") String username);
}