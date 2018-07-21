package com.nrms.RolesSpecs;

import junit.framework.Assert;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nrmswebdriverconfigration.LoadConfigration;

public class TestEmployeeRole {
	private WebDriver driver;
	private String roles_employee[]= {"Dashboard","Projects","Profile","Account","Employee Reviews"};
	@Parameters(value={"employee_user", "password"})
	@Test
  public void EmployeeRoleTest(String employee_user , String password) {
		boolean TestStatus=new TestRole().testAnyRole(roles_employee, employee_user , password);
		Assert.assertTrue("Employee Roles Test Fail",TestStatus==true);
	  
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
