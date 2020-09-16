package com.ccarlos.mapper;

import com.ccarlos.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IUserMapper {

    //根据id查询用户
    @Select({"select * from user where id = #{id}"})
    public User findUserById(Integer id);

    //查询所有用户、同时查询每个用户关联的订单信息
    @Select("select * from user")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "orderList",column = "id",javaType = List.class,
                    many=@Many(select = "com.ccarlos.mapper.IOrderMapper.findOrderByUid"))
    })
    public List<User> findAll();

    //查询所有用户、同时查询每个用户关联的角色信息
    @Select("select * from user")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "roleList",column = "id",javaType = List.class,
                    many = @Many(select = "com.ccarlos.mapper.IRoleMapper.findRoleByUid"))
    })
    public List<User> findAllUserAndRole();
}
