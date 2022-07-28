package com.servlet.admin;

import com.bean.Timetable;
import com.service.TimeTableService;
import com.service.impl.TimeTableServiceImpl;
import com.util.BeanUtils;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

public class DeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final Logger log = Logger.getLogger(DeleteServlet.class.getName());

    private final TimeTableService timeTableService = new TimeTableServiceImpl();

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        log.info("Request to delete timetable data");
        Timetable bean = new Timetable();
        BeanUtils.populate(bean, req);
        String collegeName = req.getParameter("collegeName");
        int status = timeTableService.delete(bean, collegeName);
        if (status == 404) {
            PrintWriter out = res.getWriter();
            out.print("Invalid record!");
        }
        res.setStatus(status);
    }

}
