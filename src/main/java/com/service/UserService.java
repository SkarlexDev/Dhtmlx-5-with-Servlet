package com.service;

import com.bean.User;

public interface UserService {
    public User getByUserNameAndPassword(String userName, String password);
    public boolean getUserByIDAccess(Long id);
}
