package org.freesofts.blackjack.tcpserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	private static Connection dbConnection = null;
	private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "admin";
    private static final String DB_PASSWORD = "password";
	private static final String CREATE_USER_TABLE = "CREATE TABLE user(NAME varchar(128), PASSWORD varchar(128), AMOUNT int, FLAG int)";
	
	static {
		initConnection();
	}
	
	private static void initConnection() {
		/*
		 *  This Code loads the JDBC-ODBC Bridge Driver
		 */
		
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            createUserTable(dbConnection);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }    
	}
	
	public static Connection getConnection() {
		return dbConnection;
	}
	
	private static void createUserTable(Connection connection) throws SQLException {
		PreparedStatement createPreparedStatement = connection.prepareStatement(CREATE_USER_TABLE);
        createPreparedStatement.executeUpdate();
        createPreparedStatement.close();
	}
	
	// Closes the statement created to execute the query
	public static void closeStatement(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException sqle) {
			}
		}
	}


	// Closes the result set created to execute the query
	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException sqle) {
			}
		}
	 }
}
