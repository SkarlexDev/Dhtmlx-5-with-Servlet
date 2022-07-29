package com.service;

import com.bean.Teacher;

public interface TeacherService {

	StringBuilder createXMLForm(String action);

	int create(Teacher bean);

	int update(Teacher bean, String oldName);

	int delete(Teacher bean);
}
