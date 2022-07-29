package com.dao.impl;

import com.bean.User;
import com.dao.UserDao;
import com.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class UserDaoImpl implements UserDao {

    private static final Logger log = Logger.getLogger(UserDaoImpl.class.getName());

    @Override
    public User findByUserNameAndPassword(String userName, String password) {
        log.info("Request to find User from db");
        String sql = "select * from public.user where user_name= ? and \"password\" = ?";
        User bean = new User();
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, userName);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                bean.setId(rs.getLong(1));
                bean.setUserName(rs.getString(2));
                bean.setAccessToken(rs.getString(4));
            }
            DbUtil.closeConn(rs, st, conn);
        } catch (ClassNotFoundException | SQLException e1) {
            e1.printStackTrace();
        }
        return bean;
    }

	@Override
	public User findByIdAndToken(Long id, String token) {
		log.info("Request to find User from db");
		String sql = "select * from public.user where id = ? and access_token= ?";
		User bean = null;
		try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setLong(1, id);
            st.setString(2, token);
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
            	bean = new User();
            }
            DbUtil.closeConn(rs, st, conn);
        } catch (ClassNotFoundException | SQLException e1) {
            e1.printStackTrace();
        }
        return bean;
	}

	
}
