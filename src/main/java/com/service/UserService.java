package com.service;

import com.bean.User;

import java.util.Optional;

public interface UserService {
    Optional<User> getByUserNameAndPassword(String userName, String password);

    Optional<User> getUserByIDAndAccess(Long id, String token);
}
