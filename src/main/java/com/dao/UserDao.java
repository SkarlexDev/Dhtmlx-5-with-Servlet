package com.dao;

import com.bean.User;

import java.util.Optional;

public interface UserDao {

    Optional<User> findByUserNameAndPassword(String userName, String password);

    Optional<User> findByIdAndToken(Long id, String token);
}
