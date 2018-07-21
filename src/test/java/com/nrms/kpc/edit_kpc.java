package com.nrms.kpc;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.nrms.DataReader.*;
import com.nrms.loginspecs.LoginManager;
import com.nrmswebdriverconfigration.LoadConfigration;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class edit_kpc {
	private WebDriver driver;
	
	@Test
	public void editkpc() throws Exception{
		
		// Access to Employee review
		driver.findElement(By.id("Employee_Reviews")).click();
		
		// Access to Employee > Admin
		driver.findElement(By.id("Employee_ReviewsAdmin")).click();
		
		// Access to Employee > Admin > Key Performance 
		driver.findElement(By.id("Employee_ReviewsAdminKey_Performance_Criteria")).click();
		
		//Getting list of Odd rows in grid
		List<WebElement> rows_odd = driver.findElements(By.cssSelector("tr[class='gradeA odd']"));
		
		// Getting list of Even rows in grid
		List<WebElement> rows_even = driver.findElements(By.cssSelector("tr[class='gradeA even']"));
		
		
		
		
		for (WebElement webElement : rows_odd) {
				
				// Getting all the text from odd rows
				String str_fnd = webElement.findElement(By.cssSelector("td[class='sorting_1']")).getText();
				
				//Comparing 
			if(str_fnd.equals("Additional Achievements And Accomplishments1"))
			{
				
				// After comparing text and click on edit link button
				webElement.findElement(By.cssSelector("td[align='center']")).findElement(By.id("edit")).click();
				
						
				
				update();
					
				break;
			}
				
		}
		
		
			for (WebElement webElement : rows_even) {
				
				// Getting all the text from odd rows
				String str1_find = webElement.findElement(By.cssSelector("td[class='sorting_1']")).getText();
				
				//Comparing 
			if(str1_find.equals("Additional Achievements And Accomplishments1")){
				
				// After comparing text and click on edit link button
				webElement.findElement(By.cssSelector("td[align='center']")).findElement(By.id("edit")).click();
				

				
				
		
				update();
			
				
				
				break;

			}
				
		}
			

		
	}
	public void update() throws Exception{
		
		//Getting user list from property reader
		String user_list[] = new PropertiesReader().getKpc();
		
		// Finding element
		WebElement titlename =driver.findElement(By.id("keypoint_title"));
		
		//Clearing element
		titlename.clear();
		
		//Making object of random
		Random rndm = new Random();
		
		//Initialize an integer
		int i = 10;
			int randomint = rndm.nextInt(i);
			
			//Combining random number with property reader text
			titlename.sendKeys(user_list[0] + randomint);
		
		
		
			// Finding element
		WebElement dropdown = driver.findElement(By.id("weight"));
		
		//Click on dropdown 
		dropdown.click();
		
		//Sending value to dropdown
		dropdown.sendKeys(user_list[1]);
		
		// Finding element
		WebElement dr = driver.findElement(By.id("description"));
		
		//Clearing element
		dr.clear();
		
		//Sending values
		dr.sendKeys(user_list[2]);
		
		//Clicking on update button
		driver.findElement(By.id("update")).click();
		
	}
	
	@BeforeTest
	@Parameters(value={"admin_user", "password"})
	public void getLogin (String admin_user, String password){
		
		//Making chrome object
		LoadConfigration objt_browser = new LoadConfigration("GoogleChrome");
		
		//Loading web browser
		driver = objt_browser.LoadWebDriver();
		
		//Maximize browser
		driver.manage().window().maximize();
		
		//Making object of Login manager
		LoginManager obj_callLogin = new LoginManager(driver);
		obj_callLogin.callLogin(admin_user, password);
		
	}

	 @AfterTest
	 public void QuitBrowser(){
		 
		// If alert alert display
		 if (driver.findElement(By.xpath("/html/body/main/div[3]/div[2]/div[1]")).isDisplayed()){
			
			 //Click on cancel button
			driver.findElement(By.id("cancel")).click();
			}
		 
	 }
}
