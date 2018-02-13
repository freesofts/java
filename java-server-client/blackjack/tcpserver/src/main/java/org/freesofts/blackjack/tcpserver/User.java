package org.freesofts.blackjack.tcpserver;
import java.sql.*;
import java.util.*;
public class User
{
    private static final String REGISTER_USER_QUERY = "INSERT INTO user(NAME, PASSWORD, AMOUNT, FLAG) VALUES (?, ?, ?, ?)";
	private static final String LOGIN_VALIDATE_QUERY = "Update user set Flag = ? WHERE NAME = ? and PASSWORD = ?";
	private static final String FLAG_LOGIN_QUERY = "UPDATE user SET Flag = ? WHERE Name = ?";
	private static final String AMOUNT_LOGIN_QUERY = "UPDATE user SET Amount = ? WHERE Name = ?";
	private static final String GET_AMOUNT_QUERY = "SELECT Amount FROM user WHERE Name = ?";		
	
	private static Connection getConnection() {
		return Database.getConnection();
	}
	
	public boolean register(String usernm, String passwrd) {
		boolean success = false;
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		try {

			pstmt = con.prepareStatement(REGISTER_USER_QUERY);
			pstmt.setString(1, usernm);
			pstmt.setString(2, passwrd);
			pstmt.setInt(3, 1000);
			pstmt.setInt(4,0);
			pstmt.execute();
			success = true;
		} catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getMessage());
		} finally {
			Database.closeStatement(pstmt);
		}		
		return success;
	}

	/*
	 * Creates a new connection objects and returns. If an error occurs while
	 * establishing the connection it returns null.
	 *
	 * @return	Connection database connection to execute queries.
	 */
	public int loginDB(String usernm, String passwrd){
		int flag = 1;
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		// Check whether the user is a valid user and he/she is not playing the game
		try {
			pstmt = con.prepareStatement(LOGIN_VALIDATE_QUERY);
			pstmt.setInt(1, 1);
			pstmt.setString(2, usernm);
			pstmt.setString(3, passwrd);

			int count = pstmt.executeUpdate();
			flag = count==1?0:1; 	
			System.out.println("User "+usernm+" logged in ("+flag+")");
		}
		catch (SQLException sqle) {
			System.err.println("SQLException: " + sqle.getMessage());
			// Database error
			flag = 1;
		}
		finally {
			Database.closeStatement(pstmt);
		}				
		
		return flag;
	}
	public void logout(String usernm) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;

		try {

			pstmt = con.prepareStatement(FLAG_LOGIN_QUERY);
			pstmt.setInt(1, 0);
			pstmt.setString(2, usernm);
			pstmt.execute();
		} catch (SQLException sqle) {
			// In case of duplicate rows an exception will be thrown
			// because of the unique constriant present in the database.
			System.err.println("SQLException: " + sqle.getMessage());

		} finally {
			Database.closeStatement(pstmt);
		}
	}
	public int getAmount(String usernm)
	{
		int amount=0;
		Connection con = getConnection();		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(GET_AMOUNT_QUERY);
			pstmt.setString(1, usernm);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				amount=rs.getInt("AMOUNT");
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			Database.closeResultSet(rs);
			Database.closeStatement(pstmt);
		}
		return amount;
	}
	public void setAmount(String usernm,int amount) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		try {

			pstmt = con.prepareStatement(AMOUNT_LOGIN_QUERY);
			pstmt.setInt(1, amount);
			pstmt.setString(2, usernm);
			pstmt.execute();
		} catch (SQLException sqle) {
			// In case of duplicate rows an exception will be thrown
			// because of the unique constriant present in the database.
			//System.err.println("SQLException: " + sqle.getMessage());

		} finally {
			Database.closeStatement(pstmt);
		}
	}
}