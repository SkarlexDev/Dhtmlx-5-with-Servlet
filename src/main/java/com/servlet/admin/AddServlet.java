package com.servlet.admin;

import com.bean.Timetable;
import com.service.TimeTableService;
import com.service.impl.TimeTableServiceImpl;
import com.util.BeanUtils;
import com.util.SecuredUtil;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.logging.Logger;

public class AddServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final Logger log = Logger.getLogger(AddServlet.class.getName());

    private final TimeTableService timeTableService = new TimeTableServiceImpl();

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        log.info("Request to add timetable data");
        if (SecuredUtil.allow(req.getSession(false))) {
            Timetable bean = new Timetable();
            BeanUtils.populate(bean, req);
            String collegeName = req.getParameter("collegeName");
            if (timeTableService.create(bean, collegeName)) {
                res.setStatus(HttpServletResponse.SC_ACCEPTED);
            } else {
                res.getWriter().print("Failed to save!");
                res.setStatus(HttpServletResponse.SC_CONFLICT);
            }
        } else {
            log.info("Unauthorized user!");
            res.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
