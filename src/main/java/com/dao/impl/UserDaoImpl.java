package com.dao.impl;

import com.bean.User;
import com.dao.UserDao;
import com.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Logger;

public class UserDaoImpl implements UserDao {

    private static final Logger log = Logger.getLogger(UserDaoImpl.class.getName());

    @Override
    public Optional<User> findByUserNameAndPassword(String userName, String password) {
        log.info("Request to find User from db");
        String sql = "select * from public.user where user_name= ? and \"password\" = ?";
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = DbUtil.getConnection();
            st = conn.prepareStatement(sql);
            st.setString(1, userName);
            st.setString(2, password);
            rs = st.executeQuery();
            if (rs.next()) {
                return Optional.of(createUser(rs));
            }
        } catch (ClassNotFoundException | SQLException e1) {
            e1.printStackTrace();
        } finally {
            DbUtil.closeConn(rs, st, conn);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findByIdAndToken(Long id, String token) {
        log.info("Request to find User from db");
        String sql = "select * from public.user where id = ? and access_token= ?";
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = DbUtil.getConnection();
            st = conn.prepareStatement(sql);
            st.setLong(1, id);
            st.setString(2, token);
            rs = st.executeQuery();
            if (rs.next()) {
                return Optional.of(createUser(rs));
            }
            DbUtil.closeConn(rs, st, conn);
        } catch (ClassNotFoundException | SQLException e1) {
            e1.printStackTrace();
        } finally {
            DbUtil.closeConn(rs, st, conn);
        }
        return Optional.empty();
    }

    private User createUser(ResultSet rs) throws SQLException {
        User bean = new User();
        bean.setId(rs.getLong(1));
        bean.setUserName(rs.getString(2));
        bean.setAccessToken(rs.getString(4));
        return bean;
    }
}
