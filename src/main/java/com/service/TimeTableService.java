package com.service;

import com.bean.Timetable;

public interface TimeTableService {

    public StringBuilder generateTable(String name, int year);

    public int create(Timetable e, String name);

    public int delete(Timetable e, String collegeName);

    public int update(Timetable bean, String collegeName, String oldStartTime);

}
