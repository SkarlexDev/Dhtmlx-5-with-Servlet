package com.servlet.admin;

import java.io.IOException;
import java.util.logging.Logger;

import com.service.TeacherService;
import com.service.impl.TeacherServiceImpl;
import com.util.SecuredUtil;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TeacherFormManageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(TeacherFormManageServlet.class.getName());
	
	private final TeacherService formService = new TeacherServiceImpl();

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		log.info("Request to generate teacher form manage xml");
		if (SecuredUtil.allow(req.getSession(false))) {
			res.setContentType("text/xml");
			StringBuilder xml = new StringBuilder();
			if (req.getParameter("addTeacher") != null) {
				xml = formService.createXMLForm("add");
			}
			if (req.getParameter("editTeacher") != null) {
				xml = formService.createXMLForm("edit");
			}
			if (req.getParameter("deleteTeacher") != null) {
				xml = formService.createXMLForm("del");
			}
			res.getWriter().print(xml);
		} else {
			log.info("Unauthorized user!");
			res.setStatus(HttpServletResponse.SC_FORBIDDEN);
		}
	}
}
