package com.service;

import com.bean.Teacher;

public interface TeacherService {

    StringBuilder createXMLForm(String action);

    boolean create(Teacher bean);

    boolean update(Teacher bean, String oldName);

    boolean delete(Teacher bean);
}
