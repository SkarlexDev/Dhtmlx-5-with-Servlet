package com.servlet.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import com.bean.Teacher;
import com.service.TeacherService;
import com.service.impl.TeacherServiceImpl;
import com.util.BeanUtils;
import com.util.SecuredUtil;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AddTeacherServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final Logger log = Logger.getLogger(AddTeacherServlet.class.getName());

	private final TeacherService teacherService = new TeacherServiceImpl();

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		log.info("Request to add timetable data");
		if (SecuredUtil.allow(req.getSession(false))) {
			Teacher bean = new Teacher();
			BeanUtils.populate(bean, req);
			int status = teacherService.create(bean);
			if (status == 409) {
				PrintWriter out = res.getWriter();
				out.print("Record already exists in DB!");
			}
			res.setStatus(status);
		} else {
			log.info("Unauthorized user!");
			res.setStatus(HttpServletResponse.SC_FORBIDDEN);
		}
	}
}
