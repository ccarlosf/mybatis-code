package com.ccarlos.mapper;

import com.ccarlos.pojo.User;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IUserMapper {

    public List<User> findAll();

    public List<User> findAllUserAndRole();

    //根据id查询用户
    @Select({"select * from user where id = #{id}"})
    public User findUserById(Integer id);
}
