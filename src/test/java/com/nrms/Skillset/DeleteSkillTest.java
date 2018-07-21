package com.nrms.Skillset;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.nrms.DataReader.PropertiesReader;
import com.nrms.loginspecs.LoginManager;
import com.nrmswebdriverconfigration.LoadConfigration;
import com.nrmswebdriverconfigration.NRMSConstants;

public class DeleteSkillTest {
  
	WebDriver driver;
	Skill DBSkill;
	@BeforeTest
	public void BeforeDelSkillTest() {
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
	@Test
	public void DelSkillTest() {
		String name=PropertiesReader.getValue("skillName");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		driver.findElement(By.xpath(".//*[@id='dataTables-example_filter']/label/input")).sendKeys(name);
		List<WebElement> list=driver.findElements(By.className("odd"));
		list.addAll(driver.findElements(By.className("even")));

		for (WebElement webElement : list) {
			String str = webElement.findElement(By.cssSelector("td[class='highlight sorting_1']")).getText();
			if(str.equals(name))
			{
				System.out.println(str);
				webElement.findElement(By.cssSelector("a[data-id='2'][data-title='kitty'][role='button']")).findElement(By.cssSelector("i[class='fa fa-trash']")).click();
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); 
				break;
			}
			
		}
		SkillDB obj=new SkillDB();
		DBSkill=obj.getSkill(name);
		SoftAssert softAssert=new SoftAssert();
		softAssert.assertEquals(DBSkill.title, null);
		softAssert.assertEquals(DBSkill.category,null);
		softAssert.assertEquals(DBSkill.status, null);
		softAssert.assertEquals(DBSkill.description,null );
		softAssert.assertAll();

	}
	@AfterTest
	public void AfterDelSkillTest() {
	driver.quit();
	}
}
