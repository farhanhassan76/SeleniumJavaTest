package com.nrms.Employee;
import com.nrms.DataReader.PropertiesReader;

public class DataClass {
	
	public static Employee getData  (String file) throws Exception {
		
		Employee emp=new Employee();
		
		//Populating data in Object
		emp.firstName=PropertiesReader.getValue("firstName");
		emp.lastName=PropertiesReader.getValue("lastName");
		emp.cellPersonal=PropertiesReader.getValue("cellPersonal");
		emp.emailPersonal=PropertiesReader.getValue("emailPersonal");
		emp.gender=PropertiesReader.getValue("gende");
		emp.meritalStatus=PropertiesReader.getValue("meritalStatus");
		emp.profilePicture=PropertiesReader.getValue("profilePictur");
		emp.officialName=PropertiesReader.getValue("officialName");
		emp.login=PropertiesReader.getValue("login");
		emp.status=PropertiesReader.getValue("status");
		emp.department=PropertiesReader.getValue("department");
		emp.designation=PropertiesReader.getValue("designation");
		emp.cnic=PropertiesReader.getValue("cnic");
		emp.officialCell=PropertiesReader.getValue("officialCell");
		emp.officialEmail=PropertiesReader.getValue("officialEmail");;
		emp.dateOfJoining=PropertiesReader.getValue("dateOfJoining");
		emp.careerStartDate=PropertiesReader.getValue("careerStartDate");
		emp.resume=PropertiesReader.getValue("resume");
		emp.roles=PropertiesReader.getValue("roles");
		emp.lastDegree=PropertiesReader.getValue("lastDegree");
		emp.lastInstitution=PropertiesReader.getValue("lastInstitution");
		emp.degreeCompletionDate=PropertiesReader.getValue("degreeCompletionDate");		
		return emp;
	}
}

