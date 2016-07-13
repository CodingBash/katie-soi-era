package edu.ilstu.business.era.database;

import java.sql.Connection;
import java.sql.DriverManager;

import edu.ilstu.business.era.exceptions.KatieActionFailedException;

public class DBConnection {

	private static DBConnection dbConn = null;
	
	private String dbURL = "jdbc:mysql://localhost:3306/ksi";
	
	private String userName = "root";
	
	private String password = "rishi";
	
	private Connection connnection = null;
	
	private DBConnection(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connnection = DriverManager.getConnection(dbURL,userName,password);
		}catch(Exception e){
			new KatieActionFailedException(e.getMessage());
		}
	}
	
	public static DBConnection getDBConnection(){
		if(dbConn == null)
			dbConn = new DBConnection();
		return dbConn;
	}
	
	public Connection getConnection(){
		if(connnection==null)
			getDBConnection();
		return connnection;
	}
}
