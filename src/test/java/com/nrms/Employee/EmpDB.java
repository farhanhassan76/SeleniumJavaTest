package com.nrms.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.nrms.DBConnection.DBContext;
import com.nrms.Employee.Employee;
public class EmpDB {
	Employee emp;
	ResultSet rs;
	public Employee getAddedRecord(String login){

		DBContext db=new DBContext();
		db.connect();
		String userID=null,profileID=null;
		emp = new Employee();
		try{
			
			db.statement=db.conn.createStatement();
			String query="SELECT profiles.id, profiles.first_name, profiles.last_name, profiles.cnic,"
					+ " profiles.email_official, profiles.email_personal, profiles.cell_official, "
							+ "profiles.cell_personal, profiles.gender, profiles.marital_status, "
							+ "DATE_FORMAT(profiles.joining_date, '%M %d, %Y') as joining_date, users.id, users.login, users.official_name,"
							+ "DATE_FORMAT(profiles.career_start_date, '%M %d, %Y') as career_start_date FROM profiles"
							+ " JOIN users ON users.id=profiles.user_id where users.login='"+login+"'";
			
			
			rs=db.statement.executeQuery(query); 
			if (rs.next()){
				emp.firstName=rs.getString("first_name");
				emp.lastName=rs.getString("last_name");
				emp.cellPersonal=rs.getString("cell_personal");
				emp.emailPersonal=rs.getString("email_personal");
				emp.gender=rs.getString("gender");
				emp.maritalStatus=rs.getString("marital_status");
				emp.officialName=rs.getString("official_name");
				emp.login=rs.getString("login");
				emp.cnic=rs.getString("cnic");
				emp.officialCell=rs.getString("cell_official");
				emp.officialEmail=rs.getString("email_official");
				emp.dateOfJoining=rs.getString("joining_date");
				emp.careerStartDate=rs.getString("career_start_date");
				userID=rs.getString(12);
				profileID=rs.getString(1);
				
			}
			query="SELECT desig_title FROM designations JOIN users ON users.designation_id=designations.id WHERE users.id="+userID;
			rs=db.statement.executeQuery(query); 
			if (rs.next()){
				emp.designation=rs.getString(1);
			}
			query="SELECT dept_name FROM departments JOIN users ON users.department_id=departments.id WHERE users.id="+userID;
			rs=db.statement.executeQuery(query); 
			if (rs.next()){
				emp.department=rs.getString(1);
			}
		
			query="SELECT institutions.name FROM institutions JOIN portfolios ON institutions.id=portfolios.institution_id WHERE portfolios.profile_id="+profileID;
			rs=db.statement.executeQuery(query); 
			if (rs.next()){
				emp.lastInstitution=rs.getString(1);
			}
			query="SELECT degrees.name, DATE_FORMAT(portfolios.completion_date, '%M %d, %Y') FROM degrees JOIN portfolios ON degrees.id=portfolios.degree_id WHERE portfolios.profile_id="+profileID;
			rs=db.statement.executeQuery(query); 
			if (rs.next()){
				emp.lastDegree=rs.getString(1);
				emp.degreeCompletionDate=rs.getString(2);
			}
			query="SELECT roles.title FROM roles JOIN roles_users ON roles_users.role_id=roles.id JOIN users ON roles_users.user_id=users.id WHERE users.id="+userID;
			rs=db.statement.executeQuery(query); 
			if (rs.next()){
				emp.roles=rs.getString(1);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		db.close();
		return emp;
	}
	public Employee getDeletedRecord(String officialName){
		
		DBContext db=new DBContext();
		db.connect();
		String userID=null,profileID=null;
		emp = new Employee();
		try{
			db.statement=db.conn.createStatement();
			String query="SELECT profiles.id, profiles.first_name, profiles.last_name, profiles.cnic,"
					+ " profiles.email_official, profiles.email_personal, profiles.cell_official, "
							+ "profiles.cell_personal, profiles.gender, profiles.marital_status, "
							+ "DATE_FORMAT(profiles.joining_date, '%M %d, %Y') as joining_date, users.id, users.login, users.official_name,"
							+ "DATE_FORMAT(profiles.career_start_date, '%M %d, %Y') as career_start_date FROM profiles"
							+ " JOIN users ON users.id=profiles.user_id where users.official_name='"+officialName+"'";
			
			rs=db.statement.executeQuery(query); 
			if (rs.next()){
				emp.firstName=rs.getString("first_name");
				emp.lastName=rs.getString("last_name");
				emp.cellPersonal=rs.getString("cell_personal");
				emp.emailPersonal=rs.getString("email_personal");
				emp.gender=rs.getString("gender");
				emp.maritalStatus=rs.getString("marital_status");
				emp.officialName=rs.getString("official_name");
				emp.login=rs.getString("login");
				emp.cnic=rs.getString("cnic");
				emp.officialCell=rs.getString("cell_official");
				emp.officialEmail=rs.getString("email_official");
				emp.dateOfJoining=rs.getString("joining_date");
				emp.careerStartDate=rs.getString("career_start_date");
				userID=rs.getString(12);
				profileID=rs.getString(1);
			}
			query="SELECT desig_title FROM designations JOIN users ON users.designation_id=designations.id WHERE users.id="+userID;
			rs=db.statement.executeQuery(query); 
			if (rs.next()){
				emp.designation=rs.getString(1);
			}
			query="SELECT dept_name FROM departments JOIN users ON users.department_id=departments.id WHERE users.id="+userID;
			rs=db.statement.executeQuery(query); 
			if (rs.next()){
				emp.department=rs.getString(1);
			}
		
			query="SELECT institutions.name FROM institutions JOIN portfolios ON institutions.id=portfolios.institution_id WHERE portfolios.profile_id="+profileID;
			rs=db.statement.executeQuery(query); 
			if (rs.next()){
				emp.lastInstitution=rs.getString(1);
			}
			query="SELECT degrees.name, DATE_FORMAT(portfolios.completion_date, '%M %d, %Y') FROM degrees JOIN portfolios ON degrees.id=portfolios.degree_id WHERE portfolios.profile_id="+profileID;
			rs=db.statement.executeQuery(query); 
			if (rs.next()){
				emp.lastDegree=rs.getString(1);
				emp.degreeCompletionDate=rs.getString(2);
			}
			query="SELECT roles.title FROM roles JOIN roles_users ON roles_users.role_id=roles.id JOIN users ON roles_users.user_id=users.id WHERE users.id="+userID;
			rs=db.statement.executeQuery(query); 
			if (rs.next()){
				emp.roles=rs.getString(1);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		db.close();
		return emp;
	}
}
