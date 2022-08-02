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

public class DeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final Logger log = Logger.getLogger(DeleteServlet.class.getName());

    private final TimeTableService timeTableService = new TimeTableServiceImpl();

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        log.info("Request to delete timetable data");
        if (SecuredUtil.allow(req.getSession(false))) {
            Timetable bean = new Timetable();
            BeanUtils.populate(bean, req);
            String collegeName = req.getParameter("collegeName");
            if (timeTableService.delete(bean, collegeName)) {
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
