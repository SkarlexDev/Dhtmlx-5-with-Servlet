package com.service.impl;

import com.bean.Timetable;
import com.dao.TimetableDao;
import com.dao.impl.TimeTableDaoImpl;
import com.service.TimeTableService;

import java.util.List;
import java.util.logging.Logger;

public class TimeTableServiceImpl implements TimeTableService {

    private static final Logger log = Logger.getLogger(TimeTableServiceImpl.class.getName());

    private final TimetableDao timeTableDao = new TimeTableDaoImpl();

    private String lastDay;

    @Override
    public StringBuilder generateTable(String name, int year) {
        log.info("Generating timetable data table");
        List<Timetable> list = timeTableDao.findAllByCollegeAndYear(name, year);
        StringBuilder sb = new StringBuilder();
        sb.append("<table id=\"timeTableView\"><tr><th>Day</th><th>Time</th><th>Subject</th><th>Location</th><th>Teacher</th></tr>");
        if (list.size() != 0) {
            boolean addTd = true;
            lastDay = list.get(0).getDay();
            for (Timetable dao : list) {
                sb.append("<tr>");
                if (!lastDay.equals(dao.getDay())) {
                    addTd = true;
                    lastDay = dao.getDay();
                }
                if (addTd) {
                    sb.append("<td rowspan=\"").append(list.stream().filter(p -> p.getDay().equals(lastDay)).count()).append("\">").append(dao.getDay()).append("</td>");
                    addTd = false;
                }
                sb.append("<td>").append(dao.getStartTime()).append("-").append(dao.getEndTime()).append("</td>");
                sb.append("<td>").append(dao.getSubject()).append(" (").append(dao.getSubjectType().charAt(0)).append(")").append("</td>");
                sb.append("<td>").append(dao.getBuilding()).append(" ").append(dao.getRoom()).append("</td>");
                sb.append("<td>").append(dao.getTeacher()).append("</td>");
                sb.append("</tr>");
            }
        }
        sb.append("</table>");
        return sb;
    }

    @Override
    public int create(Timetable e, String name) {
        log.info("Request to save Timetable and return status code");
        return timeTableDao.save(e, name);
    }

    @Override
    public int delete(Timetable e, String collegeName) {
        log.info("Request to delete Timetable and return status code");
        return timeTableDao.delete(e, collegeName);
    }

    @Override
    public int update(Timetable bean, String collegeName, String oldStartTime) {
        log.info("Request to update Timetable and return status code");
        return timeTableDao.update(bean, collegeName, oldStartTime);
    }
}
