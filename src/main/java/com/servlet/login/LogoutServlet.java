package com.servlet.login;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.logging.Logger;

public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final Logger log = Logger.getLogger(LogoutServlet.class.getName());

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        log.info("Request to logout");
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
            Cookie[] cookies = req.getCookies();
            if (cookies != null)
                for (Cookie cookie : cookies) {
                    cookie.setValue("");
                    cookie.setPath("/college-manager");
                    cookie.setMaxAge(0);
                    res.addCookie(cookie);
                }
        } else {
            res.sendRedirect("college");
        }
    }
}
