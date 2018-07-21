package com.nrms.DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.nrms.DataReader.PropertiesReader;
public class DBContext {
	public Connection conn;
	public Statement statement;
	
	public DBContext(){
		conn=null;
	}
	//Establish Connection with DB
	public void connect(){		
		String connectionString =PropertiesReader.getValue("connectionString");
		String username=PropertiesReader.getValue("dbUsername");
		String password=PropertiesReader.getValue("dbPassword");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(connectionString,username,password);
			System.out.println("Connection to SqlDB is successfull");
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC driver not found, please add it to your project");
			e.printStackTrace();
		} catch(SQLException e){
			System.out.println("Connection to SqlDB failed");
			e.printStackTrace();
		}
	}
	//Closing Connection
	public void close(){
		try {
			conn.close();
		} catch (SQLException e) {
			System.out.println("An error occured while closing the connection.");
			e.printStackTrace();
		}
	}
}
