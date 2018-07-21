package com.nrms.RolesSpecs;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import com.nrmswebdriverconfigration.LoadConfigration;



public class TestAdminRoles {
	private WebDriver driver;       

	
	private String roles_admin[]= {"Dashboard","Certificates","User Configurations","Employees","Departments","Designation","Clients","Customer Companies","Degrees","Institutions","Skills","Roles","Projects","Reports","Profile","Account","User Permissions","Employee Reviews","Attendance","Project Status","Project Attributes"};
	
	@Parameters({"admin_user", "password"})
	@Test
   public void AdminRolesTest(String admin_user, String password) throws Exception {    
		boolean TestStatus=new TestRole().testAnyRole(roles_admin , admin_user , password);
		Assert.assertTrue(TestStatus==true);
		
		
		
	}
		
  
	
  
 

	@BeforeTest
  public void beforeTest() {  
  	LoadConfigration config=new LoadConfigration("GoogleChrome");
		driver=config.LoadWebDriver();
  	
  }       
  @AfterTest
  public void afterTest() {   
      driver.quit();          
  }       
  
}
