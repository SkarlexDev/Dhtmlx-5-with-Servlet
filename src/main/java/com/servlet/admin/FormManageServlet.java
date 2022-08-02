package com.servlet.admin;

import com.service.FormManageService;
import com.service.impl.FormManageServiceImpl;
import com.util.SecuredUtil;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.logging.Logger;

public class FormManageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final Logger log = Logger.getLogger(FormManageServlet.class.getName());

    private final FormManageService formManageService = new FormManageServiceImpl();

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        log.info("Request to generate form manage xml");
        res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        res.setHeader("Pragma", "no-cache");
        res.setDateHeader("Expires", 0);
        if (SecuredUtil.allow(req.getSession(false))) {
            res.setContentType("text/xml");
            StringBuilder xml = new StringBuilder();
            if (req.getParameter("add") != null) {
                xml = formManageService.createXmlForm("add");
            }
            if (req.getParameter("edit") != null) {
                xml = formManageService.createXmlForm("edit");
            }
            res.getWriter().println(xml);
        } else {
            log.info("Unauthorized user!");
            res.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
