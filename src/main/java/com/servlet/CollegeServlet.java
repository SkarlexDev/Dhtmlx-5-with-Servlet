package com.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.logging.Logger;

public class CollegeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final Logger log = Logger.getLogger(CollegeServlet.class.getName());

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        log.info("Request to redirect to index.html");
        res.sendRedirect("/college-manager");
    }
}
