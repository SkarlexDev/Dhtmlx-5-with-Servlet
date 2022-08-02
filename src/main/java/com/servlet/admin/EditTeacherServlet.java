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

public class EditTeacherServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final Logger log = Logger.getLogger(EditTeacherServlet.class.getName());

    private final TeacherService teacherService = new TeacherServiceImpl();

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        log.info("Request to edit timetable data");
        if (SecuredUtil.allow(req.getSession(false))) {
            Teacher bean = new Teacher();
            BeanUtils.populate(bean, req);
            String oldName = req.getParameter("old-name");
            if (teacherService.update(bean, oldName)) {
                res.setStatus(HttpServletResponse.SC_ACCEPTED);
            } else {
                res.getWriter().print("Record already exists in DB!");
                res.setStatus(HttpServletResponse.SC_CONFLICT);
            }
        } else {
            log.info("Unauthorized user!");
            res.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
