package com.ccarlos.mapper;

import com.ccarlos.pojo.User;

import java.util.List;

public interface IUserMapper {

    public List<User> findAll();

    public List<User> findAllUserAndRole();
}
