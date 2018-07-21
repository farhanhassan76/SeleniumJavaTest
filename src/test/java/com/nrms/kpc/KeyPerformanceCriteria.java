package com.nrms.kpc;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.nrms.loginspecs.*;
import com.nrmswebdriverconfigration.*;
import com.nrms.DataReader.*;




public class KeyPerformanceCriteria {

	private WebDriver driver;
	
	
	
	
		@Test
		public void NewKeyPerformance() throws Exception{
	
			// Access to Employee review
			driver.findElement(By.id("Employee_Reviews")).click();
			
			// Access to Employee Review > Admin
			driver.findElement(By.id("Employee_ReviewsAdmin")).click();
			
			// Access to Employee Review > Admin > Key Performance criteria
			driver.findElement(By.id("Employee_ReviewsAdminNew_Key_Performance_Criteria")).click();
			
			// Getting userlist from Property reader class.
			String user_list[] = new PropertiesReader().getKpc();
			
			// Finding element and sending values from property reader
			WebElement titlename =driver.findElement(By.id("title"));
			titlename.sendKeys(user_list[0]);
			
			// Finding element and sending values from property reader
			WebElement dropdown = driver.findElement(By.id("weight"));
			dropdown.sendKeys(user_list[1]);
			
			// Finding element and sending values from property reader
			driver.findElement(By.id("description")).sendKeys(user_list[2]);
			
			
			driver.findElement(By.id("add")).click();
			
			
			
			}
		@BeforeTest
		//Getting parameter from XMl
		@Parameters(value={"admin_user", "password"})
		
		public void GetLogin(String admin_user, String password)
		{
			//Making chrome object
			LoadConfigration objt_Chrome = new LoadConfigration("GoogleChrome");
			
			//Loading web browser
			driver = objt_Chrome.LoadWebDriver();
			
			//Maximize browser
			driver.manage().window().maximize();
			
			//Making object of Login manager
			LoginManager obj_CallLogin = new LoginManager(driver);
			
			obj_CallLogin.callLogin(admin_user, password);
		
		}
		@AfterTest
		public void quitBrowser(){
			
			// If alert alert display
			if(driver.findElement(By.cssSelector("div.alert.alert-danger ul li")).isDisplayed()) {
			// Quit browser
			driver.quit();
		}
			
		}

}
