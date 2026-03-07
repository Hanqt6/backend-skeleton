package com.xiaowushi.skeleton.api.user.mapper;

import com.xiaowushi.skeleton.api.user.model.User;
import org.apache.ibatis.annotations.*;

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
}