package com.util;

import java.sql.*;
import java.util.logging.Logger;

public class DbUtil {

    private final static Logger log = Logger.getLogger(DbUtil.class.getName());

	private static final String url = "jdbc:postgresql://localhost:5432/postgres";

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
        log.info("Requesting db connection...");
        Class.forName("org.postgresql.Driver");
		String password = "1234";
		String user = "postgres";
        log.info("Connection successful");
        return DriverManager.getConnection(url, user, password);
    }

    public static void closeConn(ResultSet rs, PreparedStatement stmt, Connection con) throws SQLException {
        if (rs != null) {
            rs.close();
            log.info("ResultSet closed");
        }
        if (stmt != null) {
            stmt.close();
			log.info("Statement closed");
        }
        if (con != null) {
            con.close();
			log.info("Connection closed");
        }
    }

}
