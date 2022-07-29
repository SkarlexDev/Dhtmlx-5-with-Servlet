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
import java.io.PrintWriter;
import java.util.logging.Logger;

public class EditServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final Logger log = Logger.getLogger(EditServlet.class.getName());

	private final TimeTableService timeTableService = new TimeTableServiceImpl();

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		log.info("Request to edit timetable data");
		if (SecuredUtil.allow(req.getSession(false))) {
			Timetable bean = new Timetable();
			BeanUtils.populate(bean, req);
			String collegeName = req.getParameter("collegeName");
			String oldStartTime = req.getParameter("oldStartTime");
			int status = timeTableService.update(bean, collegeName, oldStartTime);
			if (status == 400) {
				PrintWriter out = res.getWriter();
				out.print("Invalid record!");
			}
			res.setStatus(status);
		} else {
			log.info("Unauthorized user!");
			res.setStatus(HttpServletResponse.SC_FORBIDDEN);
		}
	}
}
