package com.servlet.login;

import java.io.IOException;
import java.util.logging.Logger;

import com.bean.User;
import com.service.UserService;
import com.service.impl.UserServiceImpl;
import com.util.BeanUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final Logger log = Logger.getLogger(LoginServlet.class.getName());

	private final UserService userService = new UserServiceImpl();

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		log.info("Check if user is already logged");
		HttpSession session = req.getSession(false);
		if (session != null) {
			log.info("User is already logged redirecting to admin page");
			res.sendRedirect("admin");
		} else {
			log.info("User is not logged accessing login page");
			res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			res.setHeader("Pragma", "no-cache");
			res.setDateHeader("Expires", 0);
			req.getRequestDispatcher("login.html").forward(req, res);
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		log.info("Processing login details");
		User bean = new User();
		BeanUtils.populate(bean, req);
		HttpSession session = req.getSession(false);
		if (session != null) {
			res.sendRedirect("admin");
		} else {
			User admin = userService.getByUserNameAndPassword(bean.getUserName(), bean.getPassword());
			if (admin.getId() == null) {
				res.getWriter().print("User not found!");
				res.setStatus(HttpServletResponse.SC_FORBIDDEN);
			} else {
				session = req.getSession(true);
				session.setAttribute("user", admin);
				req.getRequestDispatcher("admin").forward(req, res);
			}
		}
	}

}
