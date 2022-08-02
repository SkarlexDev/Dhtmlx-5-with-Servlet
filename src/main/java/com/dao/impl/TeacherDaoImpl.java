package com.dao.impl;

import com.bean.Teacher;
import com.dao.TeacherDao;
import com.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class TeacherDaoImpl implements TeacherDao {

    private static final Logger log = Logger.getLogger(TeacherDaoImpl.class.getName());

    @Override
    public Optional<Teacher> findByName(String name) {
        log.info("Request to Teacher by name");
        String sql = "select * from public.teachers where \"name\" = ?";
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = DbUtil.getConnection();
            st = conn.prepareStatement(sql);
            st.setString(1, name);
            rs = st.executeQuery();
            if (rs.next()) {
                return Optional.of(createTeacher(rs));
            }
        } catch (ClassNotFoundException | SQLException e1) {
            e1.printStackTrace();
        } finally {
            DbUtil.closeConn(rs, st, conn);
        }
        return Optional.empty();
    }

    private Teacher createTeacher(ResultSet rs) throws SQLException {
        Teacher bean = new Teacher();
        bean.setId(rs.getLong(1));
        bean.setTeacherName(rs.getString(2));
        return bean;
    }

    @Override
    public List<Teacher> findAll() {
        log.info("Request to find all Teachers from db");
        String sql = "SELECT * FROM public.teachers";
        List<Teacher> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = DbUtil.getConnection();
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            Teacher bean;
            while (rs.next()) {
                bean = new Teacher();
                bean.setId(rs.getLong(1));
                bean.setTeacherName(rs.getString(2));
                list.add(bean);
            }
        } catch (ClassNotFoundException | SQLException e1) {
            e1.printStackTrace();
        } finally {
            DbUtil.closeConn(rs, st, conn);
        }
        return list;
    }

    @Override
    public boolean save(Teacher bean) {
        log.info("Creating college: " + bean.getTeacherName());
        if (findByName(bean.getTeacherName()).isPresent()) {
            return false;
        }
        String sql = "INSERT INTO PUBLIC.teachers (\"name\") VALUES (?);";
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DbUtil.getConnection();
            st = conn.prepareStatement(sql);
            st.setString(1, bean.getTeacherName());
            return st.executeUpdate() > 0;
        } catch (ClassNotFoundException | SQLException e1) {
            log.info("Duplicate key found");
            return false;
        } finally {
            DbUtil.closeConn(null, st, conn);
        }
    }

    @Override
    public boolean update(Teacher bean, String oldName) {
        log.info("Request to update Teacher");
        String sql = "update public.teachers set \"name\" = ? where \"name\" = ?";
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DbUtil.getConnection();
            st = conn.prepareStatement(sql);
            st.setString(1, bean.getTeacherName());
            st.setString(2, oldName);
            return st.executeUpdate() > 0;
        } catch (ClassNotFoundException | SQLException e1) {
            log.info("Duplicate key found");
            return false;
        } finally {
            DbUtil.closeConn(null, st, conn);
        }
    }

    @Override
    public boolean delete(Teacher bean) {
        log.info("Request to delete Teacher");
        String sql = "Delete from public.teachers where \"name\" = ?";
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DbUtil.getConnection();
            st = conn.prepareStatement(sql);
            st.setString(1, bean.getTeacherName());
            return st.executeUpdate() > 0;
        } catch (ClassNotFoundException | SQLException e1) {
            log.info("No key found");
            return false;
        } finally {
            DbUtil.closeConn(null, st, conn);
        }
    }
}
