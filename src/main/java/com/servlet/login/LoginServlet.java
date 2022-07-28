package com.servlet.login;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.logging.Logger;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final Logger log = Logger.getLogger(LoginServlet.class.getName());

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

}
