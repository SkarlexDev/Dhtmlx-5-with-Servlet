package com.dao;

import com.bean.User;

public interface UserDao {
    public User findByUserNameAndPassword(String userName, String password);
    
    public User findByIdAndToken(Long id, String token);
}
