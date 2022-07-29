package com.dao;

import java.util.List;

import com.bean.Teacher;

public interface TeacherDao {

	public List<Teacher> findAll();

	public int save(Teacher bean);

	public int update(Teacher bean, String oldName);

	public int delete(Teacher bean);

}
