package com.service.impl;

import com.bean.Teacher;
import com.dao.CollegeDao;
import com.dao.TeacherDao;
import com.dao.impl.CollegeDaoImpl;
import com.dao.impl.TeacherDaoImpl;
import com.service.FormManageService;

import java.util.logging.Logger;

public class FormManageServiceImpl implements FormManageService {

	private static final Logger log = Logger.getLogger(FormManageServiceImpl.class.getName());

	private final CollegeDao collegeDao = new CollegeDaoImpl();
	
	private final TeacherDao teacherDao = new TeacherDaoImpl();

	@Override
	public StringBuilder createXmlForm(String action) {
		log.info("Request to generate form xml");
		StringBuilder xml = new StringBuilder();
		xml.append("<?xml version=\"1.0\"?>");
		xml.append("<items><item type=\"settings\" inputWidth=\"220\" position=\"label-left\" labelWidth=\"110\" />");
		if (action.equals("add")) {
			xml.append("<item type=\"label\" label=\"Add new Timetable data\" labelWidth=\"220\"/>");
		} else {
			xml.append("<item type=\"label\" label=\"Edit Timetable data\" labelWidth=\"220\" />");
		}
		xml.append("<item type=\"label\" label=\"College\" />");
		xml.append("<item type=\"select\" label=\"College\" name=\"collegeName\" offsetLeft=\"15\" required=\"true\">");
		for (String item : collegeDao.findAllName()) {
			xml.append("<option text=\"").append(item).append("\" value=\"").append(item).append("\" />");
		}
		xml.append("</item>");
		xml.append(
				"<item type=\"input\" label=\"Study Year\" name=\"studyYear\" offsetLeft=\"15\" required=\"true\" /> "
						+ "<item type=\"label\" label=\"Date\" /> "
						+ "<item type=\"select\" label=\"Select Day\" offsetLeft=\"15\" name=\"day\" required=\"true\"> "
						+ "<option text=\"Monday\" value=\"Monday\" /> "
						+ "<option text=\"Tuesday\" value=\"Tuesday\" /> "
						+ "<option text=\"Wednesday\" value=\"Wednesday\" /> "
						+ "<option text=\"Thursday\" value=\"Thursday\" /> "
						+ "<option text=\"Friday\" value=\"Friday\" />" + "</item>");
		if (!action.equals("add")) {
			xml.append(
					"<item type=\"input\" label=\"Old Start Time\" name=\"oldStartTime\" offsetLeft=\"15\" value=\"00:00\" maxLength=\"5\" required=\"true\" /> ");
		}
		xml.append(
				"<item type=\"input\" label=\"Start Time\" name=\"startTime\" offsetLeft=\"15\" value=\"00:00\" maxLength=\"5\" required=\"true\" /> "
						+ "<item type=\"input\" label=\"End Time\" name=\"endTime\" offsetLeft=\"15\" value=\"00:00\" maxLength=\"5\" required=\"true\" /> "
						+ "<item type=\"label\" label=\"Details\" /> <item type=\"input\" label=\"Subject\" name=\"subject\" offsetLeft=\"15\" required=\"true\" /> "
						+ "<item type=\"select\" label=\"Subject type\" offsetLeft=\"15\" name=\"subjectType\" required=\"true\"> "
						+ "<option text=\"Course\" value=\"Course\" /> "
						+ "<option text=\"Laboratory\" value=\"Laboratory\" /> "
						+ "<option text=\"Project\" value=\"Project\" /> "
						+ "<option text=\"Seminary\" value=\"Seminary\" />" + "</item>");
		xml.append("<item type=\"select\" label=\"Teacher\" offsetLeft=\"15\" name=\"teacher\" required=\"true\">");
		
		for (Teacher teacher : teacherDao.findAll()) {
			xml.append("<option text=\"").append(teacher.getTeacherName()).append("\" value=\"").append(teacher.getTeacherName()).append("\" />");
		}
		
		xml.append("</item>");
		xml.append(
				"<item type=\"input\" label=\"Building\" name=\"building\" offsetLeft=\"15\" maxLength=\"2\" required=\"true\" /> "
						+ "<item type=\"input\" label=\"Room\" name=\"room\" offsetLeft=\"15\" maxLength=\"20\" required=\"true\" />");
		if (action.equals("add")) {
			xml.append("<item type=\"button\" value=\"Add\" offsetTop=\"15\" offsetLeft=\"230\" />");
		} else {
			xml.append("<item type=\"button\" value=\"Edit\" offsetTop=\"15\" offsetLeft=\"230\" />");
		}
		xml.append("</items>");
		return xml;
	}

}
