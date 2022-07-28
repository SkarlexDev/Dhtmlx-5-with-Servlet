package com.servlet.admin;

import com.bean.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.logging.Logger;

public class AdminServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final Logger log = Logger.getLogger(AdminServlet.class.getName());

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        log.info("Verify if user can access admin page");
        HttpSession session = req.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");
            if (user != null) {
                log.info("User is logged giving resources");
                req.getRequestDispatcher("/WEB-INF/restricted/admin.html").forward(req, res);
            }
        } else {
            log.info("User is not logged redirecting...");
            res.sendRedirect("login");
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");
            if (user != null) {
                res.setStatus(HttpServletResponse.SC_ACCEPTED);
            }
        } else {
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

    }

}
