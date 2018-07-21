package com.nrms.loginspecs;
import junit.framework.Assert;

import org.testng.annotations.Test;
import org.openqa.selenium.By;      
import org.openqa.selenium.WebDriver;       
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.nrmswebdriverconfigration.*;
public class LoginFailureTest {
	
	private String falseusername="reqreq";
	private String falsepassword="789";
	private WebDriver driver; 
	private String login_pagetitle="NRMS | Login - Sign in";  
	    @Test              
	    public void testNRMS_LoginFail() throws InterruptedException {    
	       
	        boolean test_status[]= new boolean[3];
	        test_status[0]= testfalseusernamecheck();
	        test_status[1]= testfalsepasswordcheck();
	        test_status[2]= testfalseusernamefalsepasswordcheck();
	       
	        Assert.assertTrue("Login with false username test is failed",(test_status[0]!=false));
	        Assert.assertTrue("Login with false password test is failed",test_status[1]!=false); 
	        Assert.assertTrue("Login with false password and false username Test is failed",test_status[2]!=false); 

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
	  public boolean  testfalseusernamecheck()
	
	  {
		    driver.get(NRMSConstants.SERVER_URL);
		    WebElement email=driver.findElement(By.id("username"));
	        email.sendKeys(falseusername);
	        WebElement password=driver.findElement(By.id("password"));
	        password.sendKeys("123");
	        driver.findElement(By.id("login")).click();
	        String title=driver.getTitle();
	        if(title.contains(login_pagetitle))
	        {
	        	checkalertPresent();
	        	return true;
	        }
	        	
	        else 
	        	return false;
	       
	  }
	  public boolean  testfalsepasswordcheck()
		
	  {
		    driver.get(NRMSConstants.SERVER_URL);
		    WebElement email=driver.findElement(By.id("username"));
	        email.sendKeys("waseem.jaffer");
	        WebElement password=driver.findElement(By.id("password"));
	        password.sendKeys(falsepassword);
	        driver.findElement(By.id("login")).click();
	        String title=driver.getTitle();
	        if(title.contains(login_pagetitle))
	        {
	        	checkalertPresent();
	        	return true;
	        }
	        	
	        else 
	        	return false;
	      
	  }
	  
	  public boolean testfalseusernamefalsepasswordcheck()
	  	
	  {
		  driver.get(NRMSConstants.SERVER_URL); 
		  WebElement email=driver.findElement(By.id("username"));
	        email.sendKeys(falseusername);
	        WebElement password=driver.findElement(By.id("password"));
	        password.sendKeys(falsepassword);
	        driver.findElement(By.id("login")).click();
	        String title=driver.getTitle();
	        if(title.contains(login_pagetitle))
	        {
	        	checkalertPresent();
	        	return true;
	        }
	        	
	        else 
	        	return false;
	  }
	  public void checkalertPresent()
	  {
		 
		  WebElement alertelement= driver.findElement (By.className("alert-danger"));
		  WebDriverWait wait = new WebDriverWait(driver,120);
		  wait.until(ExpectedConditions.visibilityOf(alertelement));
		    
	  }

}
