package com.dao;

import java.util.List;

import com.bean.Timetable;

public interface TimetableDao {

    public int save(Timetable e, String collegeName);

    public List<Timetable> findAllByCollegeAndYear(String collegeName, int year);

    public int delete(Timetable e, String collegeName);

    public int update(Timetable e, String collegeName, String oldStartTime);
}
