package com.nrms.RolesSpecs;

import junit.framework.Assert;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nrmswebdriverconfigration.LoadConfigration;

public class TestQAMRole {
	private WebDriver driver;
	private String roles_qam[]= {"Dashboard","Employees","Clients","Projects","Profile","Account","Reports","Employee Reviews"};
	
	
	@Parameters(value={"qam_user", "password"})
	@Test
  public void QAMRoleTest(String qam_user , String password) {
		boolean TestStatus=new TestRole().testAnyRole(roles_qam, qam_user , password);
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
