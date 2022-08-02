package com.dao;

import com.bean.Timetable;

import java.util.List;

public interface TimetableDao {

    boolean save(Timetable e, String collegeName);

    List<Timetable> findAllByCollegeAndYear(String collegeName, int year);

    boolean delete(Timetable e, String collegeName);

    boolean update(Timetable e, String collegeName, String oldStartTime);
}
