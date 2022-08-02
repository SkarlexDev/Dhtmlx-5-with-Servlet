package com.service.impl;

import com.bean.User;
import com.dao.UserDao;
import com.dao.impl.UserDaoImpl;
import com.service.UserService;
import com.servlet.admin.AdminServlet;
import com.util.PasswordUtil;

import java.util.Optional;
import java.util.logging.Logger;

public class UserServiceImpl implements UserService {

    private final Logger log = Logger.getLogger(AdminServlet.class.getName());

    private final UserDao userDao = new UserDaoImpl();

    @Override
    public Optional<User> getByUserNameAndPassword(String userName, String password) {
        log.info("Request to get user");
        String encoded = PasswordUtil.hashPassword(password);
        return userDao.findByUserNameAndPassword(userName, encoded);
    }

    @Override
    public Optional<User> getUserByIDAndAccess(Long id, String token) {
        return userDao.findByIdAndToken(id, token);
    }
}
