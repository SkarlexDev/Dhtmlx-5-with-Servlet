package com.servlet.admin;

import com.bean.College;
import com.service.CollegeService;
import com.service.impl.CollegeServiceImpl;
import com.util.BeanUtils;
import com.util.SecuredUtil;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

public class NewCollegeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final Logger log = Logger.getLogger(NewCollegeServlet.class.getName());

    private final CollegeService collegeService = new CollegeServiceImpl();

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        log.info("Request to create new college");
        if (SecuredUtil.allow(req.getSession(false))) {
            College bean = new College();
            BeanUtils.populate(bean, req);
            PrintWriter out = res.getWriter();
            String verification = bean.getCollegeName().replaceAll("\\D", "");

            if (verification.length() > 0) {
                out.print("Digits are not allowed!");
                res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            } else {
                if (collegeService.create(bean)) {
                    res.setStatus(HttpServletResponse.SC_ACCEPTED);
                } else {
                    out.print("Record already exists in DB!");
                    res.setStatus(HttpServletResponse.SC_CONFLICT);
                }
            }
        } else {
            log.info("Unauthorized user!");
            res.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
