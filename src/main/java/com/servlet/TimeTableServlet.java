package com.servlet;

import com.bean.College;
import com.service.CollegeService;
import com.service.TimeTableService;
import com.service.impl.CollegeServiceImpl;
import com.service.impl.TimeTableServiceImpl;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

public class TimeTableServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final Logger log = Logger.getLogger(TimeTableServlet.class.getName());

    private final TimeTableService timeTableService = new TimeTableServiceImpl();
    private final CollegeService collegeService = new CollegeServiceImpl();

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        log.info("Request to generate college Timetable view from db");
        String name = req.getParameterNames().nextElement();
        Optional<College> bean = collegeService.findByName(name);
        name = name.replaceAll("\\D", "");
        if (bean.isPresent() && !name.equals("")) {
            res.getWriter().println(timeTableService.generateTable(bean.get().getCollegeName(), Integer.parseInt(name)));
        }
    }
}
