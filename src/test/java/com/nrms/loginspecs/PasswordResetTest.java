package com.nrms.loginspecs;


import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;      
import org.openqa.selenium.WebDriver;       
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.nrmswebdriverconfigration.*;
public class PasswordResetTest {
	private WebDriver driver;    
	private String page_url=NRMSConstants.PASSWORDRESETPAGE;  
	private String pagetitle="NRMS | Login";
	
	@Test              
    public void testForgetPassword() throws InterruptedException {    
		boolean test_status[]= new boolean[3];
        test_status[0]= correctusernamereset();
        test_status[1]= falseusernamereset();
        test_status[2]= invalidemailreset();
        Assert.assertTrue((test_status[0]!=false),"correct username password reset test is Failed"); 
        Assert.assertTrue(test_status[1]!=false,"incorrect email format test is failed"); 
	    Assert.assertTrue(test_status[2]!=false,"invalid email format test is failed"); 

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
    
    public boolean correctusernamereset()
    {
    	
		driver.get(page_url);  
		WebElement email_reset=driver.findElement(By.name("email"));
		email_reset.sendKeys("HS@G.COM");
		driver.findElement(By.tagName("button")).click();
		 String title=driver.getTitle(); 
		if(title.contains(pagetitle))
	        {
	        	alertpresent(true);
	        	return true;
	        }
	        return false;
	    
    }
    public boolean falseusernamereset()
    {
    	
		driver.get(page_url);  
		WebElement email_reset=driver.findElement(By.name("email"));
        email_reset.sendKeys("test@gmmail.com");
        driver.findElement(By.tagName("button")).click();
        String title=driver.getTitle();
        if(title.contains(pagetitle))
        {
        	alertpresent(false);
        	return true;
        }
        return false;
    }
    public boolean invalidemailreset()
    {
    	
		driver.get(page_url);  
		WebElement email_reset=driver.findElement(By.cssSelector("input[name='email']"));
        email_reset.sendKeys("test@gmmiiiii");
        driver.findElement(By.tagName("button")).click();
        String title=driver.getTitle();
        if(title.contains(pagetitle))
        {
        	alertpresent(false);
        	return true;
        }
        return false;
    }
    
    public void alertpresent(boolean alert_status)
    {
    	  if (alert_status==false)
    	  {
    	  WebElement alertelement= driver.findElement (By.className("alert-danger"));
		  WebDriverWait wait = new WebDriverWait(driver,1200);
		  wait.until(ExpectedConditions.visibilityOf(alertelement));
    	  }
		  else if(alert_status==true)
		  {
			  WebElement alertelement= driver.findElement (By.className("alert-success"));
			  WebDriverWait wait = new WebDriverWait(driver,1200);
			  wait.until(ExpectedConditions.visibilityOf(alertelement));
		  }
    }
}   
	

