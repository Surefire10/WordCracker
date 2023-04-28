package application;

import java.sql.*;


public class DatabaseConnection {
	
	
	public Connection databaseLink;
	
	
	public Connection getConnection() {
		
		String databaseName = "wordcracker";
		String databaseUsername = "root";
		String databasePassword = "T0H3ll@ndB@ck";
		String url = "jdbc:mysql://localhost:3306/" + databaseName; 
		
		try {
			
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			databaseLink = DriverManager.getConnection(url,databaseUsername,databasePassword);
		
		
		
		
		} catch (Exception e) {
			
			
			System.out.println("idk");
			
			
		}
	

		return databaseLink;
	

	}
	
}
