package com.servlet.login;

import com.bean.User;
import com.service.UserService;
import com.service.impl.UserServiceImpl;
import com.util.BeanUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.logging.Logger;

public class ProcessLoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final Logger log = Logger.getLogger(ProcessLoginServlet.class.getName());
    private final UserService userService = new UserServiceImpl();

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
