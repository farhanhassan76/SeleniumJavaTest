package com.nrms.Skillset;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.nrms.DataReader.PropertiesReader;
import com.nrms.loginspecs.LoginManager;
import com.nrmswebdriverconfigration.LoadConfigration;
import com.nrmswebdriverconfigration.NRMSConstants;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;

public class AddSkillTest {
  
	WebDriver driver;
	Skill skillData, DBSkill;
	@BeforeTest
	public void beforeAddSkillTest()
	{
		LoadConfigration config=new LoadConfigration("FireFox");
		driver=config.LoadWebDriver();
		driver.get(NRMSConstants.SERVER_URL);
		driver.manage().window().maximize();
		String username=PropertiesReader.getValue("username");
		String password=PropertiesReader.getValue("password");
		//Login 
		LoginManager login=new LoginManager(driver);
		login.callLogin(username,password);
		//Clicking on "Employee" Link
		driver.findElement(By.linkText("Skills")).click();
		//Clicking on "New" Link
		driver.findElement(By.linkText("New")).click();	
	}
	
	@Test(priority=0)
	public void addSkillTest() {
		skillData=Skill.get();
		SoftAssert softAssert = new SoftAssert();		
	    add(driver,skillData);
	    softAssert.assertTrue(checkVisibility("p[class='alert alert-success']"), "Message not displayed");
	    SkillDB obj= new SkillDB();
	    
	    DBSkill=obj.getSkill(skillData.title);
	    
	    
		softAssert.assertEquals(DBSkill.title, skillData.title);
		softAssert.assertEquals(DBSkill.category, skillData.category);
		softAssert.assertEquals(DBSkill.status, skillData.status);
		softAssert.assertEquals(DBSkill.description,skillData.description );
		softAssert.assertAll();
	    
	}
	@Test(priority=1)
	public void AlreadyExistTest(){
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); 
		skillData=Skill.get();

		//Clicking on "New" Link
		driver.findElement(By.linkText("New")).click();
		add(driver,skillData);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); 

		//Clicking on "New" Link
		driver.findElement(By.linkText("New")).click();
		add(driver,skillData);
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(checkVisibility("div[class='alert alert-danger']"), "Message not displayed");
		softAssert.assertAll();		
	}	
	@AfterTest
	public void afterSkillTest() {
		driver.quit();
	}
	
	
	public void add(WebDriver driver, Skill skillData){
		WebDriverWait wait = new WebDriverWait(driver,3200);
	    wait.until(ExpectedConditions.visibilityOfElementLocated((By.name("skill_name"))));
		driver.findElement(By.name("skill_name")).sendKeys(skillData.title);
	    new Select(driver.findElement(By.name("category_id"))).selectByVisibleText(skillData.category);
	    new Select(driver.findElement(By.name("sc_id"))).selectByVisibleText(skillData.status);
	    JavascriptExecutor jse = (JavascriptExecutor)driver;
	    jse.executeScript("window.scrollBy(0,-500)", "");
	    driver.findElement(By.cssSelector("textarea[class='form-control']")).sendKeys(skillData.description);
	    driver.findElement(By.cssSelector("input[class='btn btn-primary'][type='submit']")).click();

	}
	public Boolean checkVisibility(String css){
		WebElement error=null;
		error=driver.findElement(By.cssSelector(css));
		if(error!=null)
			return true;
		else
			return false;					
	}

}
