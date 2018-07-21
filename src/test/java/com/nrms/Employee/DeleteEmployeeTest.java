package com.nrms.Employee;


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

public class DeleteEmployeeTest {
	WebDriver driver;
	Employee DBEmployee;
	@BeforeTest
	public void beforeDeleteEmpTest(){
		
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
		driver.findElement(By.linkText("Employees")).click();
		//Clicking on "List All" Link
		driver.findElement(By.linkText("List All")).click();	
				
	}
	@Test
	public void deleteEmpTest(){
	
		String name=PropertiesReader.getValue("empName");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		driver.findElement(By.xpath(".//*[@id='dataTables-example_filter']/label/input")).sendKeys(name);
		List<WebElement> list=driver.findElements(By.className("odd"));
		list.addAll(driver.findElements(By.className("even")));

		for (WebElement webElement : list) {
			String str = "   "+webElement.findElement(By.cssSelector("td[class='highlight sorting_1']")).getText();
			if(str.equals(name))
			{
				System.out.println(str);
				webElement.findElement(By.cssSelector("a[data-id='2'][data-title='kitty'][role='button']")).findElement(By.cssSelector("i[class='fa fa-trash']")).click();
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); 
				break;
			}
			
		}
		EmpDB obj=new EmpDB();
		DBEmployee=obj.getDeletedRecord(name);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(DBEmployee.firstName, null);
		softAssert.assertEquals(DBEmployee.lastName, null);
		softAssert.assertEquals(DBEmployee.cellPersonal, null);
		softAssert.assertEquals(DBEmployee.emailPersonal,null);
		softAssert.assertEquals(DBEmployee.gender, null);
		softAssert.assertEquals(DBEmployee.maritalStatus, null);
		
		//softAssert.assertTrue(empData.officialName.contains(DBEmployee.officialName), "Official Name not Added");
		softAssert.assertEquals(DBEmployee.login, null);
		softAssert.assertEquals(DBEmployee.department, null);
		softAssert.assertEquals(DBEmployee.designation, null);
		softAssert.assertEquals(DBEmployee.cnic, null);
		softAssert.assertEquals(DBEmployee.officialCell,null);
		softAssert.assertEquals(DBEmployee.officialEmail, null);
		softAssert.assertEquals(DBEmployee.dateOfJoining, null);
		softAssert.assertEquals(DBEmployee.careerStartDate, null);
		softAssert.assertEquals(DBEmployee.roles, null);
		softAssert.assertEquals(DBEmployee.lastDegree, null);
		softAssert.assertEquals(DBEmployee.lastInstitution, null);
		softAssert.assertEquals(DBEmployee.degreeCompletionDate,null);
	
		softAssert.assertAll();
		
	}
	@AfterTest
    public void afterDeleteEmpTest() {
        driver.quit();          
    } 
	
}
