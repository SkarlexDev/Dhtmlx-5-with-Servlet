package com.dao.impl;

import com.bean.Timetable;
import com.dao.TimetableDao;
import com.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class TimeTableDaoImpl implements TimetableDao {

    private final Logger log = Logger.getLogger(TimeTableDaoImpl.class.getName());

    @Override
    public boolean save(Timetable e, String collegeName) {
        log.info("Request to save " + e + "with college name " + collegeName);
        String sql = "INSERT INTO PUBLIC.DATA_TABLE (DAY_NAME, SUBJECT, SUBJECT_TYPE, TEACHER, BUILDING, ROOM, START_TIME, END_TIME, TIME_TABLE) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, (SELECT ID FROM PUBLIC.TIME_TABLE WHERE COLLEGE_ID = "
                + "(SELECT ID FROM PUBLIC.COLLEGE WHERE COLLEGE_NAME = ?) AND STUDY_YEAR = ?))";
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DbUtil.getConnection();
            st = conn.prepareStatement(sql);
            st.setString(1, e.getDay());
            st.setString(2, e.getSubject());
            st.setString(3, e.getSubjectType());
            st.setString(4, e.getTeacher());
            st.setString(5, e.getBuilding());
            st.setString(6, e.getRoom());
            st.setString(7, e.getStartTime());
            st.setString(8, e.getEndTime());
            st.setString(9, collegeName);
            st.setInt(10, e.getStudyYear());
            return st.executeUpdate() > 0;
        } catch (ClassNotFoundException | SQLException e1) {
            log.info("Failed to insert data!");
            return false;
        } finally {
            DbUtil.closeConn(null, st, conn);
        }
    }

    @Override
    public List<Timetable> findAllByCollegeAndYear(String collegeName, int year) {
        log.info("Request to find all timetable data based on college name and year");
        String sql = "SELECT * FROM PUBLIC.DATA_TABLE WHERE TIME_TABLE = "
                + "(SELECT ID FROM PUBLIC.TIME_TABLE WHERE COLLEGE_ID = "
                + "(SELECT ID FROM PUBLIC.COLLEGE WHERE COLLEGE_NAME = ?) "
                + "GROUP BY ID HAVING STUDY_YEAR = ?) ORDER BY " + "CASE " + "WHEN day_name = 'Monday' THEN 1 "
                + "WHEN day_name = 'Tuesday' THEN 2 " + "WHEN day_name = 'Wednesday' THEN 3 "
                + "WHEN day_name = 'Thursday' THEN 4 " + "WHEN day_name = 'Friday' THEN 5 " + "END ASC "
                + ", start_time;";
        List<Timetable> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = DbUtil.getConnection();
            st = conn.prepareStatement(sql);
            st.setString(1, collegeName);
            st.setInt(2, year);
            rs = st.executeQuery();
            Timetable e;
            while (rs.next()) {
                e = new Timetable();
                e.setId(rs.getLong(1));
                e.setDay(rs.getString(2));
                e.setSubject(rs.getString(3));
                e.setTeacher(rs.getString(4));
                e.setBuilding(rs.getString(5));
                e.setRoom(rs.getString(6));
                e.setStartTime(rs.getString(7));
                e.setEndTime(rs.getString(8));
                e.setSubjectType(rs.getString(9));
                e.setStudyYear(year);
                list.add(e);
            }
        } catch (ClassNotFoundException | SQLException e1) {
            e1.printStackTrace();
        } finally {
            DbUtil.closeConn(rs, st, conn);
        }
        return list;
    }

    @Override
    public boolean delete(Timetable e, String collegeName) {
        log.info("Request to delete from data Timetable");
        String sql = "Delete FROM PUBLIC.DATA_TABLE WHERE TIME_TABLE = "
                + "(SELECT ID FROM PUBLIC.TIME_TABLE WHERE COLLEGE_ID = "
                + "(SELECT ID FROM PUBLIC.COLLEGE WHERE COLLEGE_NAME = ?) "
                + "GROUP BY ID HAVING STUDY_YEAR = ?) and start_time= ? and day_name = ?;";
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DbUtil.getConnection();
            st = conn.prepareStatement(sql);
            st.setString(1, collegeName);
            st.setInt(2, e.getStudyYear());
            st.setString(3, e.getStartTime());
            st.setString(4, e.getDay());
            return st.executeUpdate() > 0;
        } catch (ClassNotFoundException | SQLException e1) {
            log.info("No key found");
            return false;
        } finally {
            DbUtil.closeConn(null, st, conn);
        }
    }

    @Override
    public boolean update(Timetable e, String collegeName, String oldStartTime) {
        log.info("Request to update data Timetable");
        String sql = "Update public.data_table "
                + "set subject = ?, teacher= ?, building= ?, room= ?, start_time= ?, end_time= ?, subject_type= ? "
                + "WHERE TIME_TABLE = (SELECT ID FROM PUBLIC.TIME_TABLE WHERE COLLEGE_ID = "
                + "(SELECT ID FROM PUBLIC.COLLEGE WHERE COLLEGE_NAME = ?) "
                + "GROUP BY ID HAVING STUDY_YEAR = ?) and day_name = ? and start_time= ?;";
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DbUtil.getConnection();
            st = conn.prepareStatement(sql);
            st.setString(1, e.getSubject());
            st.setString(2, e.getTeacher());
            st.setString(3, e.getBuilding());
            st.setString(4, e.getRoom());
            st.setString(5, e.getStartTime());
            st.setString(6, e.getEndTime());
            st.setString(7, e.getSubjectType());
            st.setString(8, collegeName);
            st.setInt(9, e.getStudyYear());
            st.setString(10, e.getDay());
            st.setString(11, oldStartTime);
            return st.executeUpdate() > 0;
        } catch (ClassNotFoundException | SQLException e1) {
            log.info("Failed to update!");
            return false;
        } finally {
            DbUtil.closeConn(null, st, conn);
        }
    }
}
