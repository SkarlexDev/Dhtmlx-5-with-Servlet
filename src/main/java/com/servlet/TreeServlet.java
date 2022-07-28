package com.servlet;

import com.service.TreeService;
import com.service.impl.TreeServiceImpl;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.logging.Logger;

public class TreeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final Logger log = Logger.getLogger(TreeServlet.class.getName());

    private final TreeService treeServiceImpl = new TreeServiceImpl();

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        log.info("Request to generate tree element xml from db");
        res.setContentType("text/xml");
        StringBuilder xml = treeServiceImpl.createXmlTree();
        res.getWriter().println(xml);
    }
}
