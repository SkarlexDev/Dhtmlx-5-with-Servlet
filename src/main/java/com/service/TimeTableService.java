package com.service;

import com.bean.Timetable;

public interface TimeTableService {

    StringBuilder generateTable(String name, int year);

    boolean create(Timetable e, String name);

    boolean delete(Timetable e, String collegeName);

    boolean update(Timetable bean, String collegeName, String oldStartTime);

}
