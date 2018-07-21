package com.nrms.loginspecs;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.openqa.selenium.By;      
import org.openqa.selenium.WebDriver;       
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.nrmswebdriverconfigration.*;
public class LoginTest {
	protected WebDriver driver;       
   
	
	@Test              
    public void testNRMLogin() throws IOException, FileNotFoundException,InterruptedException  {    
        
		String csvFile = "login.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		String pagetitle="User Dashboard";
		br = new BufferedReader(new FileReader(csvFile));
		while ((line = br.readLine()) != null) {
			{
				String[] user_list = line.split(cvsSplitBy);
				//
					if(user_list[2].equals("pm"))
				{
					System.out.println( "Username:"+user_list[0]+"\tPassword:"+user_list[1]);
					Loginwithusernamepassword(user_list[0],user_list[1]);
					String page_title= driver.getTitle();
			        Assert.assertTrue(page_title.contains(pagetitle),"Login with PM is failed");
			        new LoginManager(driver).callLogout();

			         
				}
				if(user_list[2].equals("sm"))
				{
					System.out.println( "Username:"+user_list[0]+"\tPassword:"+user_list[1]);
					Loginwithusernamepassword(user_list[0],user_list[1]);
					String page_title= driver.getTitle();
			        Assert.assertTrue(page_title.contains(pagetitle),"Login with SM is failed");
			        new LoginManager(driver).callLogout();
				}
				if(user_list[2].equals("qam"))
				{
					System.out.println( "Username:"+user_list[0]+"\tPassword:"+user_list[1]);
					Loginwithusernamepassword(user_list[0],user_list[1]);
					String page_title= driver.getTitle();
			        Assert.assertTrue(page_title.contains(pagetitle),"Login with QAM is failed");
			        new LoginManager(driver).callLogout();
				}
				
				if(user_list[2].equals("hr"))
				{
					//System.out.println( "Username:"+user_list[0]+"\tPassword:"+user_list[1]);
					//Loginwithusernamepassword(user_list[0],user_list[1]);
					//String page_title= driver.getTitle();
			        //Assert.assertTrue("Login with HR is failed",page_title.contains(pagetitle));
					// new LoginManager(driver).callLogout();
				}
				if(user_list[2].equals("employee"))
				{
					System.out.println( "Username:"+user_list[0]+"\tPassword:"+user_list[1]);
					Loginwithusernamepassword(user_list[0],user_list[1]);
					String page_title= driver.getTitle();
			        Assert.assertTrue(page_title.contains(pagetitle),"Login employee is failed");
			        new LoginManager(driver).callLogout();
				}
				if(user_list[2].equals("admin"))
				{
					System.out.println( "Username:"+user_list[0]+"\tPassword:"+user_list[1]);
					Loginwithusernamepassword(user_list[0],user_list[1]);
					String page_title= driver.getTitle();
			        Assert.assertTrue(page_title.contains(pagetitle),"Login with admin is failed");
			       new LoginManager(driver).callLogout();
			        
			       
				}
				
			}
			
			
		}
		br.close();
     
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
      public void Loginwithusernamepassword(String user_name,String user_password) throws InterruptedException
      {
    	  driver.get(NRMSConstants.SERVER_URL);  
    	  WebElement email=driver.findElement(By.id("username"));
          email.sendKeys(user_name);
          WebElement password=driver.findElement(By.id("password"));
          password.sendKeys(user_password);
          driver.findElement(By.id("login")).click();
          
      }
   
}   
