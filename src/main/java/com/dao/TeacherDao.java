package com.dao;

import com.bean.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherDao {

    List<Teacher> findAll();

    boolean save(Teacher bean);

    boolean update(Teacher bean, String oldName);

    boolean delete(Teacher bean);

    Optional<Teacher> findByName(String name);

}
