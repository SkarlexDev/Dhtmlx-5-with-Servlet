package com.util;

import com.bean.User;
import com.service.UserService;
import com.service.impl.UserServiceImpl;

import jakarta.servlet.http.HttpSession;

public class SecuredUtil {

	private final static UserService userService = new UserServiceImpl();
	
	public static boolean allow(HttpSession session) {
		if (session != null) {
			User user = (User) session.getAttribute("user");
			if (user != null) {
				return userService.getUserByIDAccess(user.getId());
			}
		}
		return false;
	}
}
