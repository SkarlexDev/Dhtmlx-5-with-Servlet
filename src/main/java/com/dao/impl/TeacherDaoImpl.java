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

}
