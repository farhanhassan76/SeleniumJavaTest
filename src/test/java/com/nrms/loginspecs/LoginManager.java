package com.nrms.loginspecs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.nrmswebdriverconfigration.NRMSConstants;

public class LoginManager {
	
	private WebDriver driver;
	
	public LoginManager(WebDriver driver)
	{
		this.driver=driver;
	}
   
	 public void callLogout()
	   {
		   driver.findElement(By.id("logout")).click();
	   }
	 public void callLogin(String username, String Password)
		{
			
			driver.get(NRMSConstants.SERVER_URL);  
	  	    WebElement email=driver.findElement(By.id("username"));
	        email.sendKeys(username);
	        WebElement password=driver.findElement(By.id("password"));
	        password.sendKeys(Password);
	        driver.findElement(By.tagName("button")).click();
		}

}
   