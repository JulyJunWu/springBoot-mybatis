package com.ws.dao;

import com.ws.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * 可以去掉@Component
 */
@Mapper
@Component
public interface UserMapper {

    int addUser(User user);

    @Select("select * from sys_user where id = #{id}")
    User selectOne(@Param("id") String id);

}
