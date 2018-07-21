package com.nrms.Skillset;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.nrms.DataReader.PropertiesReader;
import com.nrms.loginspecs.LoginManager;
import com.nrmswebdriverconfigration.LoadConfigration;
import com.nrmswebdriverconfigration.NRMSConstants;

import org.testng.annotations.BeforeTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;

public class EditSkillTest {
  
	WebDriver driver;
	Skill DBSkill,skillData;
	@BeforeTest
	public void beforeSkillEditTest() {
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
	  //Clicking on "List All" Link
	  driver.findElement(By.linkText("List All")).click();
	}
	
	@Test(priority=0)
	public void skillEditTest() {
  
		String name=PropertiesReader.getValue("skill_name");
		skillData=Skill.getForEdit(); 
		SoftAssert softAssert=new SoftAssert();
		edit(driver, name, skillData);
		softAssert.assertTrue(checkVisibility("p[class='alert alert-success']"), "Message not displayed");
		SkillDB obj=new SkillDB();
		DBSkill=obj.getSkill(skillData.title);
		
		softAssert.assertEquals(DBSkill.title, skillData.title);
		softAssert.assertEquals(DBSkill.category,skillData.category);
		softAssert.assertEquals(DBSkill.status, skillData.status);
		softAssert.assertEquals(DBSkill.description,skillData.description);
		softAssert.assertAll();
	}
	@Test(priority=1)
	public void skillExistsEditTest(){
		String skillOne=PropertiesReader.getValue("skill_one");
		String skillTwo=PropertiesReader.getValue("skill_two");
		skillData=Skill.getForEdit();
		edit(driver, skillOne, skillData);
		edit(driver, skillTwo, skillData);
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(checkVisibility("div[class='alert alert-danger']"), "Message not displayed");
		softAssert.assertAll();	
	}
	
	@AfterTest
	public void afterSkillEditTest() {
		driver.quit();
	}

	public void edit(WebDriver driver, String name, Skill skillData){

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath(".//*[@id='dataTables-example_filter']/label/input")).sendKeys(name);
		List<WebElement> list=driver.findElements(By.className("odd"));
		list.addAll(driver.findElements(By.className("even")));
		for (WebElement webElement : list) {
			String str = webElement.findElement(By.cssSelector("td[class='highlight sorting_1']")).getText();
			if(str.equals(name))
			{
				System.out.println(str);
				webElement.findElement(By.cssSelector("i[class='fa fa-edit']")).click();
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); 
				break;
			}		
		}
		WebDriverWait wait = new WebDriverWait(driver,3200);
		wait.until(ExpectedConditions.visibilityOfElementLocated((By.name("skill_name"))));
		driver.findElement(By.name("skill_name")).clear();
		driver.findElement(By.name("skill_name")).sendKeys(skillData.title);
	    new Select(driver.findElement(By.name("category_id"))).selectByVisibleText(skillData.category);
	    new Select(driver.findElement(By.name("sc_id"))).selectByVisibleText(skillData.status);
	    JavascriptExecutor jse = (JavascriptExecutor)driver;
	    jse.executeScript("window.scrollBy(0,-500)", "");
	    driver.findElement(By.cssSelector("textarea[class='form-control']")).clear();
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
