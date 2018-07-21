package com.nrms.RolesSpecs;

import junit.framework.Assert;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nrmswebdriverconfigration.LoadConfigration;

public class TestHRRoles {
  
	private WebDriver driver;
	private String roles_hr[]= {"Dashboard","Certificates","User Configurations","Employees","Departments","Designation","Clients","Customer Companies","Degrees","Institutions","Skills","Roles","Projects","Reports","Profile","Account","User Permissions","Employee Reviews","Attendance","Project Status","Project Attributes"};
	@Parameters(value={"hr_user", "password"})
	@Test
  public void HRRolesTest(String hr_user , String password) {
		
			boolean TestStatus=new TestRole().testAnyRole(roles_hr, hr_user , password);
			Assert.assertTrue("HR Roles Test Fail",TestStatus==true);
		  
	  
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
