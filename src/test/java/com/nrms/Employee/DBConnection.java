package com.nrms.Employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.nrms.DataReader.PropertiesReader;
import com.nrms.Employee.Employee;
public class DBConnection {
	Connection conn;
	DBConnection(){
		conn=null;
	}
	//Establish Connection with DB
	public Connection connect(){
		Connection conn=null;
		String connectionString =PropertiesReader.getValue("connectionString");
		String username=PropertiesReader.getValue("dbUsername");
		String password=PropertiesReader.getValue("dbPassword");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Creating Connection");
			conn = DriverManager.getConnection(connectionString,username,password);
			System.out.println("Connection to SqlDB is successfull");
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC driver not found, please add it to your project");
			e.printStackTrace();
		} catch(SQLException e){
			System.out.println("Connection to SqlDB failed");
			e.printStackTrace();
		}
		return conn;
	}
	//Closing Connection
	public void close(Connection conn){
		try {
			System.out.println("Closing Connection");
			conn.close();
		} catch (SQLException e) {
			System.out.println("An error occured while closing the connection.");
			e.printStackTrace();
		}
	}
	public Employee getRecord(Connection conn, String fullName){

		ResultSet rs=null;
		String userID=null,desID=null,depID=null;
		Employee emp = new Employee();
		try{
			System.out.println("Creating Statement");
			Statement statement=conn.createStatement();
			String query="SELECT profiles.first_name, profiles.last_name, profiles.cnic, profiles.email_official, profiles.email_personal, profiles.cell_official, profiles.cell_personal, profiles.gender, profiles.marital_status, DATE_FORMAT(profiles.joining_date, '%M %d,%Y'), users.id, users.login, users.official_name,DATE_FORMAT(profiles.career_start_date, '%M %d,%Y') FROM profiles JOIN users ON users.id=profiles.user_id where users.name='"+fullName+"'";
			//String query="SHOW TABLES FROM nrms_final";
			System.out.println("Getting Result");
			rs=statement.executeQuery(query); 
			if (rs.next()){
				emp.firstName=rs.getString(1);
				emp.lastName=rs.getString(2);
				emp.cellPersonal=rs.getString(7);
				emp.emailPersonal=rs.getString(5);
				emp.gender=rs.getString(8);
				emp.meritalStatus=rs.getString(9);
				//emp.profilePicture=rs.getString();
				emp.officialName=rs.getString(13);
				emp.login=rs.getString(12);
				emp.cnic=rs.getString(3);
				emp.officialCell=rs.getString(6);
				emp.officialEmail=rs.getString(4);
				emp.dateOfJoining=rs.getString(10);
				emp.careerStartDate=rs.getString(14);
				userID=rs.getString(11);
				//emp.resume=rs.getString();
				/*emp.status=rs.getString();				
				
				emp.roles=rs.getString();
				emp.lastDegree=rs.getString();
				emp.lastInstitution=rs.getString();
				emp.degreeCompletionDate=rs.getString();*/
				
				System.out.println(rs.getString(1)+"---"+rs.getString(2)+"---" +rs.getString(3)+"---" +rs.getString(4)+"---" +rs.getString(5)+"---" +rs.getString(6)+"---" +rs.getString(7)+"---" +rs.getString(8)+"---" +rs.getString(9)+"---" +rs.getString(10)+"---" +rs.getString(11)+"---" +rs.getString(12)+"---" +rs.getString(13)+"---" +rs.getString(14));
			}
			query="SELECT designation_id, department_id FROM users WHERE id="+userID;
			rs=statement.executeQuery(query); 
			if (rs.next()){
				desID=rs.getString(1);
				depID=rs.getString(2);
			}
			query="SELECT desig_title FROM designations WHERE id="+desID;
			rs=statement.executeQuery(query); 
			if (rs.next()){
				emp.designation=rs.getString("desig_title");
			}
			query="SELECT dept_name FROM departments WHERE id="+depID;
			rs=statement.executeQuery(query); 
			if (rs.next()){
				emp.department=rs.getString("dept_name");
			}

			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return emp;
	}
}
