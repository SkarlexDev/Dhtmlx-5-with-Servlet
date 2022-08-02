package com.dao.impl;

import com.bean.College;
import com.dao.CollegeDao;
import com.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class CollegeDaoImpl implements CollegeDao {

    private static final Logger log = Logger.getLogger(CollegeDaoImpl.class.getName());

    @Override
    public Optional<College> findByName(String name) {
        log.info("Request to find College by name from db");
        String sql = "SELECT * FROM public.college where college_name = ?";
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = DbUtil.getConnection();
            st = conn.prepareStatement(sql);
            st.setString(1, name);
            rs = st.executeQuery();
            if (rs.next()) {
                return Optional.of(createCollege(rs));
            }
        } catch (ClassNotFoundException | SQLException e1) {
            e1.printStackTrace();
        } finally {
            DbUtil.closeConn(rs, st, conn);
        }
        return Optional.empty();
    }

    private College createCollege(ResultSet rs) throws SQLException {
        College bean = new College();
        bean.setId(rs.getLong(1));
        bean.setCollegeName(rs.getString(2));
        bean.setYears(rs.getInt(3));
        return bean;
    }

    @Override
    public boolean save(College e) {
        log.info("Creating college: " + e.getCollegeName());
        if (findByName(e.getCollegeName()).isPresent()) {
            return false;
        }
        String sql = "insert into public.college (college_name, years) VALUES (?, ?);";
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DbUtil.getConnection();
            st = conn.prepareStatement(sql);
            st.setString(1, e.getCollegeName());
            st.setInt(2, e.getYears());
            st.executeUpdate();
        } catch (ClassNotFoundException | SQLException e1) {
            log.info("Failed to save duplicated value!");
        } finally {
            DbUtil.closeConn(null, st, conn);
        }
        createCollegeYears(e);
        return true;
    }

    private void createCollegeYears(College e) {
        log.info("Request to generate timetables for college based of years");
        StringBuilder insertCollegeYears = new StringBuilder();
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DbUtil.getConnection();
            for (int i = 1; i <= e.getYears(); i++) {
                insertCollegeYears.append("insert into public.time_table (study_year, college_id) VALUES (").append(i)
                        .append(", (Select id from public.college where college_name = '").append(e.getCollegeName())
                        .append("'));");
            }
            st = conn.prepareStatement(insertCollegeYears.toString());
            st.executeUpdate();
        } catch (ClassNotFoundException | SQLException e1) {
            e1.printStackTrace();
        } finally {
            DbUtil.closeConn(null, st, conn);
        }
    }

    @Override
    public List<College> findAll() {
        log.info("Request to find all College from db");
        String sql = "SELECT * FROM public.college";
        List<College> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs;
        try {
            conn = DbUtil.getConnection();
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            College bean;
            while (rs.next()) {
                bean = new College();
                bean.setId(rs.getLong(1));
                bean.setCollegeName(rs.getString(2));
                bean.setYears(rs.getInt(3));
                list.add(bean);
            }
        } catch (ClassNotFoundException | SQLException e1) {
            e1.printStackTrace();
        } finally {
            DbUtil.closeConn(null, st, conn);
        }
        return list;
    }

    @Override
    public List<String> findAllName() {
        log.info("Request to find all College names");
        String sql = "Select college_name from public.college";
        List<String> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs;
        try {
            conn = DbUtil.getConnection();
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(1));
            }
        } catch (ClassNotFoundException | SQLException e1) {
            e1.printStackTrace();
        } finally {
            DbUtil.closeConn(null, st, conn);
        }
        return list;
    }
}
