package com.ccarlos.mapper;

import com.ccarlos.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@CacheNamespace
public interface IUserMapper {

    //根据id查询用户
    @Select({"select * from user where id = #{id}"})
    public User findUserById(Integer id);

    //查询所有用户、同时查询每个用户关联的订单信息
    @Select("select * from user")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "orderList", column = "id", javaType = List.class,
                    many = @Many(select = "com.ccarlos.mapper.IOrderMapper.findOrderByUid"))
    })
    public List<User> findAll();

    //查询所有用户、同时查询每个用户关联的角色信
    @Select("select * from user")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "roleList", column = "id", javaType = List.class,
                    many = @Many(select = "com.ccarlos.mapper.IRoleMapper.findRoleByUid"))
    })
    public List<User> findAllUserAndRole();

    //添加用户
    @Insert("insert into user_bak values(#{id},#{username})")
    public void addUser(User user);

    //更新用户
    @Update("update user_bak set username = #{username} where id = #{id}")
    public void updateUser(User user);

    //查询用户
    @Select("select * from user_bak")
    public List<User> selectUser();

    //删除用户
    @Delete("delete from user_bak where id = #{id}")
    public void deleteUser(Integer id);
}
