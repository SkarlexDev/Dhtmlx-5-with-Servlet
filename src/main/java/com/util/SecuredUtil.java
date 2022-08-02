package com.util;

import java.util.Optional;

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
				Optional<User> admin = userService.getUserByIDAndAccess(user.getId(),user.getAccessToken());
				return admin.isPresent();
			}
		}
		return false;
	}
}
