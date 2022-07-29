package com.dao.impl;

import com.bean.College;
import com.dao.CollegeDao;
import com.util.DbUtil;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CollegeDaoImpl implements CollegeDao {

    private static final Logger log = Logger.getLogger(CollegeDaoImpl.class.getName());

    @Override
    public int save(College e) {
        log.info("Creating college: " + e.getCollegeName());
        String sql = "insert into public.college (college_name, years) VALUES (?, ?);";
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, e.getCollegeName());
            st.setInt(2, e.getYears());
            st.executeUpdate();
            DbUtil.closeConn(null, st, conn);
        } catch (ClassNotFoundException | SQLException e1) {
            log.info("Failed to save duplicated value!");
            return HttpServletResponse.SC_CONFLICT;
        }
        createCollegeYears(e);
        return HttpServletResponse.SC_ACCEPTED;
    }

    private void createCollegeYears(College e) {
        log.info("Request to generate timetables for college based of years");
        StringBuilder insertCollegeYears = new StringBuilder();
        try (Connection conn = DbUtil.getConnection()) {
            for (int i = 1; i <= e.getYears(); i++) {
                insertCollegeYears.append("insert into public.time_table (study_year, college_id) VALUES (")
                        .append(i)
                        .append(", (Select id from public.college where college_name = '")
                        .append(e.getCollegeName()).append("'));");
            }
            PreparedStatement st = conn.prepareStatement(insertCollegeYears.toString());
            st.executeUpdate();
            DbUtil.closeConn(null, st, conn);
        } catch (ClassNotFoundException | SQLException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public List<College> findAll() {
        log.info("Request to find all College from db");
        String sql = "SELECT * FROM public.college";
        List<College> list = new ArrayList<>();
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            College bean;
            while (rs.next()) {
                bean = new College();
                bean.setId(rs.getLong(1));
                bean.setCollegeName(rs.getString(2));
                bean.setYears(rs.getInt(3));
                list.add(bean);
            }
            DbUtil.closeConn(rs, st, conn);
        } catch (ClassNotFoundException | SQLException e1) {
            e1.printStackTrace();
        }
        return list;
    }

    @Override
    public College findByName(String name) {
        log.info("Request to find College by name from db");
        String sql = "SELECT * FROM public.college where college_name = ?";
        College bean = new College();
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                bean = new College();
                bean.setId(rs.getLong(1));
                bean.setCollegeName(rs.getString(2));
                bean.setYears(rs.getInt(3));
            }
            DbUtil.closeConn(rs, st, conn);
        } catch (ClassNotFoundException | SQLException e1) {
            e1.printStackTrace();
        }
        return bean;
    }

    @Override
    public List<String> findAllName() {
        log.info("Request to find all College names");
        String sql = "Select college_name from public.college";
        List<String> list = new ArrayList<>();
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(1));
            }
            DbUtil.closeConn(rs, st, conn);
        } catch (ClassNotFoundException | SQLException e1) {
            e1.printStackTrace();
        }
        return list;
    }
}
