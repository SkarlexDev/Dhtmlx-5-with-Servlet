package com.servlet.admin;

import com.bean.Teacher;
import com.service.TeacherService;
import com.service.impl.TeacherServiceImpl;
import com.util.BeanUtils;
import com.util.SecuredUtil;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.logging.Logger;

public class DeleteTeacherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final Logger log = Logger.getLogger(DeleteServlet.class.getName());

    private final TeacherService teacherService = new TeacherServiceImpl();

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        log.info("Request to delete teacher ");
        if (SecuredUtil.allow(req.getSession(false))) {
            Teacher bean = new Teacher();
            BeanUtils.populate(bean, req);
            if (teacherService.delete(bean)) {
                res.setStatus(HttpServletResponse.SC_ACCEPTED);
            } else {
                res.getWriter().print("Record not found!");
                res.setStatus(HttpServletResponse.SC_CONFLICT);
            }
        } else {
            log.info("Unauthorized user!");
            res.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
