package com.service.impl;

import com.bean.Teacher;
import com.dao.TeacherDao;
import com.dao.impl.TeacherDaoImpl;
import com.service.TeacherService;

public class TeacherServiceImpl implements TeacherService {

	private final TeacherDao teacherDao = new TeacherDaoImpl();

	@Override
	public StringBuilder createXMLForm(String action) {
		StringBuilder xml = new StringBuilder();
		xml.append("<?xml version=\"1.0\"?>");
		xml.append("<items><item type=\"settings\" inputWidth=\"220\" position=\"label-left\" labelWidth=\"110\" />");
		if (action.equals("add")) {
			xml.append("<item type=\"label\" label=\"Add new Teacher data\" labelWidth=\"220\"/>");
			xml.append(
					"<item type=\"input\" label=\"Name\" name=\"teacherName\" offsetLeft=\"15\" maxLength=\"30\" required=\"true\" /> ");
			xml.append("<item type=\"button\" value=\"Add\" offsetTop=\"15\" offsetLeft=\"230\" />");
		}
		if (action.equals("edit")) {
			xml.append("<item type=\"label\" label=\"Edit Teacher data\" labelWidth=\"220\"/>");
			xml.append(
					"<item type=\"select\" label=\"Old name\" name=\"oldname\" offsetLeft=\"15\" maxLength=\"30\" required=\"true\"> ");

			for (Teacher teacher : teacherDao.findAll()) {
				xml.append("<option text=\"").append(teacher.getTeacherName()).append("\" value=\"")
						.append(teacher.getTeacherName()).append("\" />");
			}

			xml.append("</item>");
			xml.append(
					"<item type=\"input\" label=\"Name\" name=\"teacherName\" offsetLeft=\"15\" maxLength=\"30\" required=\"true\" /> ");
			xml.append("<item type=\"button\" value=\"Edit\" offsetTop=\"15\" offsetLeft=\"230\" />");
		}
		if (action.equals("del")) {
			xml.append("<item type=\"label\" label=\"Delete Teacher \" labelWidth=\"220\"/>");
			xml.append(
					"<item type=\"select\" label=\"Old name\" name=\"teacherName\" offsetLeft=\"15\" maxLength=\"30\" required=\"true\"> ");

			for (Teacher teacher : teacherDao.findAll()) {
				xml.append("<option text=\"").append(teacher.getTeacherName()).append("\" value=\"")
						.append(teacher.getTeacherName()).append("\" />");
			}
			xml.append("</item>");
			xml.append("<item type=\"button\" value=\"Delete\" offsetTop=\"15\" offsetLeft=\"230\" />");
		}
		xml.append("</items>");
		return xml;
	}

	@Override
	public int create(Teacher bean) {
		return teacherDao.save(bean);
	}

	@Override
	public int update(Teacher bean, String oldName) {
		return teacherDao.update(bean, oldName);
	}

	@Override
	public int delete(Teacher bean) {
		return teacherDao.delete(bean);
	}

}
