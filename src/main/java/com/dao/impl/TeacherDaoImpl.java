package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.bean.Teacher;
import com.dao.TeacherDao;
import com.util.DbUtil;

import jakarta.servlet.http.HttpServletResponse;

public class TeacherDaoImpl implements TeacherDao {

	private static final Logger log = Logger.getLogger(TeacherDaoImpl.class.getName());

	@Override
	public List<Teacher> findAll() {
		log.info("Request to find all Teachers from db");
		String sql = "SELECT * FROM public.teachers";
		List<Teacher> list = new ArrayList<>();
		try (Connection conn = DbUtil.getConnection()) {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			Teacher bean;
			while (rs.next()) {
				bean = new Teacher();
				bean.setId(rs.getLong(1));
				bean.setTeacherName(rs.getString(2));
				list.add(bean);
			}
			DbUtil.closeConn(null, st, conn);
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		return list;
	}

	@Override
	public int save(Teacher bean) {
		log.info("Creating college: " + bean.getTeacherName());
		String sql = "INSERT INTO PUBLIC.teachers (\"name\") VALUES (?);";
		try (Connection conn = DbUtil.getConnection()) {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, bean.getTeacherName());
			st.executeUpdate();
			DbUtil.closeConn(null, st, conn);
		} catch (ClassNotFoundException | SQLException e1) {
			log.info("Failed to save duplicated value!");
			return HttpServletResponse.SC_CONFLICT;
		}
		return HttpServletResponse.SC_ACCEPTED;
	}

	@Override
	public int update(Teacher bean, String oldName) {
		log.info("Request to update Teacher");
		String sqlFind = "select * from public.teachers where \"name\" = ? or \"name\" = ?";
		String sql = "update public.teachers set \"name\" = ? where \"name\" = ?";
		try (Connection conn = DbUtil.getConnection()) {
			PreparedStatement st = conn.prepareStatement(sqlFind);
			st.setString(1, bean.getTeacherName());
			st.setString(2, oldName);
			ResultSet rs = st.executeQuery();
			if (rs.next() == true && rs.next() == false) {
				PreparedStatement sts = conn.prepareStatement(sql);
				sts.setString(1, bean.getTeacherName());
				sts.setString(2, oldName);
				sts.executeUpdate();
				sts.close();
			} else {
				return HttpServletResponse.SC_CONFLICT;
			}
			DbUtil.closeConn(null, st, conn);
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
			return HttpServletResponse.SC_BAD_REQUEST;
		}
		return HttpServletResponse.SC_ACCEPTED;
	}

	@Override
	public int delete(Teacher bean) {
		log.info("Request to delete Teacher");
		String sqlFind = "select * from public.teachers where \"name\" = ?";
		String sql = "Delete from public.teachers where \"name\" = ?";
		try (Connection conn = DbUtil.getConnection()) {
			PreparedStatement st = conn.prepareStatement(sqlFind);
			st.setString(1, bean.getTeacherName());
			ResultSet rs = st.executeQuery();
			if (rs.next() == true) {
				PreparedStatement sts = conn.prepareStatement(sql);
				sts.setString(1, bean.getTeacherName());
				sts.executeUpdate();
				sts.close();
			} else {
				return HttpServletResponse.SC_CONFLICT;
			}
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
			return HttpServletResponse.SC_BAD_REQUEST;
		}
		return HttpServletResponse.SC_ACCEPTED;
	}
}
