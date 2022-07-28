package com.servlet.admin;

import com.service.FormManageService;
import com.service.impl.FormManageServiceImpl;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.logging.Logger;

public class FormManageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final Logger log = Logger.getLogger(FormManageServlet.class.getName());

    private final FormManageService formManageService = new FormManageServiceImpl();

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        log.info("Request to generate form manage xml");
        res.setContentType("text/xml");
        StringBuilder xml = new StringBuilder();
        if (req.getParameter("add") != null) {
            xml = formManageService.createXmlForm("add");
        }
        if (req.getParameter("edit") != null) {
            xml = formManageService.createXmlForm("edit");
        }
        res.getWriter().println(xml);
    }

}
