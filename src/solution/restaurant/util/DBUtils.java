package solution.restaurant.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DBUtils {
	
	private final static String DB_url= "jdbc:mysql://localhost:3306/reservation_system";
	private final static String DB_User= "root";
	private final static String DB_Password= "Bx134";
	
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("mysql driver loaded");
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("sql driver not loaded");
		}
	}
	
	public static Connection getConn(){
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_url, DB_User, DB_Password);
			System.out.println("Connection successful");
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Error in connection:" + e.getMessage());
		}
		return conn;
	}
	
	public static void closeResources(PreparedStatement ps, ResultSet rs, Connection conn){
		
		try {
			if(ps !=null){				
					ps.close();
				}
			if(rs !=null){				
				rs.close();
			}
			if(conn !=null){				
				conn.close();
			}
			}catch (SQLException e) {
					e.printStackTrace();
				}
		}
	
	
	public static void main(){
		
		DBUtils.getConn();
		
	}
}
