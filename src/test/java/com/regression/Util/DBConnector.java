/**
 * 
 */
package com.regression.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//import AppiumVerification.DBUtil.DBUtil;

/**
* <pre>
* com.regression.Util
*   |_ DBConnector.java
* </pre>
* 
* Desc : 
* @Company : Wemakeprice. Co.
* @Author  : June, Ahn
* @Date    : 2017. 2. 16. 오전 9:56:04
* @Version : 
*/
public class DBConnector {
	public static void main(String[] args) throws Exception {
		getConnection();
		createPassTable(); // 테이블 생성
		createFaultTable(); // 테이블 생성
		
	}
	
	public static void deleteData() throws SQLException{
//		Connection connection = null;
		
		try {
			Connection con = getConnection();
			Statement stmt = con.createStatement();
			String sql1 = "DELETE FROM IOS_REGRESSION_PASS_TABLE";
			String sql2 = "DELETE FROM IOS_REGRESSION_FAIL_TABLE";

			// Table 초기화
			stmt.execute(sql1);
			stmt.execute(sql2);
			
			// Table 시퀀스 키 초기화
			stmt.execute("alter table IOS_REGRESSION_PASS_TABLE auto_increment=1");
			stmt.execute("alter table IOS_REGRESSION_FAIL_TABLE auto_increment=1");
			
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public static void dbPassUpdate(String str1, String str2) throws Exception {
		try {
			Connection con = getConnection();
			String sql = "INSERT INTO IOS_REGRESSION_PASS_TABLE"
					+ "(TEST, NAME) "
					+ "Values (?, ?)";

			PreparedStatement st = con.prepareStatement(sql);
			
			st.setString(1, str1);
			st.setString(2, str2);
			
			st.executeUpdate();
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public static void dbFailUpdate(String str1, String str2) throws Exception {
		try {
			Connection con = getConnection();
			String sql = "INSERT INTO IOS_REGRESSION_FAIL_TABLE"
					+ "(TEST, NAME) "
					+ "Values (?, ?)";

			PreparedStatement st = con.prepareStatement(sql);
			
			st.setString(1, str1);
			st.setString(2, str2);
			
			st.executeUpdate();
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public static void createPassTable() throws Exception {
		try {
			Connection con = getConnection();
			
			String sql = "CREATE TABLE IF NOT EXISTS IOS_REGRESSION_PASS_TABLE"
					+ "(id int NOT NULL "
					+ "AUTO_INCREMENT, "
					+ "TEST VARCHAR(255) NOT NULL DEFAULT '', "
					+ "NAME VARCHAR(255) NOT NULL DEFAULT '', "
										
					+ "TIME TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP, "
					+ "PRIMARY KEY(id))";
			
			PreparedStatement create = con.prepareStatement(sql);
			create.executeUpdate();
		} catch(Exception e){
			System.out.println(e);
		}
		finally{
			System.out.println("Function complete.");
		}
	}
	
	
	public static void createFaultTable() throws Exception {
		try {
			Connection con = getConnection();
			
			String sql = "CREATE TABLE IF NOT EXISTS IOS_REGRESSION_FAIL_TABLE"
					+ "(id int NOT NULL "
					+ "AUTO_INCREMENT, "
					
					+ "TEST VARCHAR(255) NOT NULL DEFAULT '', "
					+ "NAME VARCHAR(255) NOT NULL DEFAULT '', "
										
					+ "TIME TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP, "
					+ "PRIMARY KEY(id))";
			
			PreparedStatement create = con.prepareStatement(sql);
			create.executeUpdate();
		} catch(Exception e){
			System.out.println(e);
		}
		finally{
			System.out.println("Function complete.");
		}
	}
	
	public static Connection getConnection() throws Exception {
		try {
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost/faultlocalization";
			String username = "root";
			String password = "1q2w3e";
			Class.forName(driver);
			
			Connection conn = DriverManager.getConnection(url,username,password);
//			System.out.println("Connected");
			return conn;
		} catch(Exception e){
			System.out.println(e);
		}
		
		return null;
	}
}
