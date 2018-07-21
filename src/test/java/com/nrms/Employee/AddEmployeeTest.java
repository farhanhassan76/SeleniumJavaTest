package com.nrms.Employee;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.nrms.DataReader.PropertiesReader;
import com.nrms.loginspecs.LoginManager;
import com.nrmswebdriverconfigration.LoadConfigration;
import com.nrmswebdriverconfigration.NRMSConstants;

public class AddEmployeeTest {
	WebDriver driver;
	Employee empData, DBEmployee;
	@BeforeTest
	public void beforeAddEmpTest()
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
		driver.findElement(By.linkText("Employees")).click();
		//Clicking on "New" Link
		driver.findElement(By.linkText("New")).click();	
	}
	@Test
	public void addEmpTest() 
	{
		empData=DataClass.getData ();
		WebDriverWait wait = new WebDriverWait(driver,3200);
	    wait.until(ExpectedConditions.visibilityOfElementLocated((By.id("fname"))));
	
	    driver.findElement(By.id("fname")).sendKeys(empData.firstName);
	    
	    driver.findElement(By.id("lname")).sendKeys(empData.lastName);
	    
	    driver.findElement(By.name("cell_personal")).sendKeys(empData.cellPersonal);
	    driver.findElement(By.name("email_personal")).sendKeys(empData.emailPersonal);
	    if(empData.gender.equals("Female")){
	    	driver.findElement(By.xpath(".//*[@id='personal']/div/div/div[5]/label[3]/div/span")).click();
	    }	
	    new Select(driver.findElement(By.name("marital_status"))).selectByVisibleText(empData.maritalStatus);
	    
		JavascriptExecutor jse = (JavascriptExecutor)driver;
	    jse.executeScript("window.scrollBy(0,-700)", "");
	    driver.findElement(By.linkText("Official Information")).click();;
	    
	    wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("official_name"))));
	    
	    driver.findElement(By.id("official_name")).sendKeys(empData.firstName);
	    driver.findElement(By.id("login")).sendKeys(empData.login);
	    new Select(driver.findElement(By.name("department"))).selectByVisibleText(empData.department);
	    new Select(driver.findElement(By.name("designation"))).selectByVisibleText(empData.designation);
	    
	    String [] cnic=empData.cnic.split("-");
	    
	    driver.findElement(By.id("cnic1")).clear();
	    driver.findElement(By.id("cnic1")).sendKeys(cnic[0]);
	    driver.findElement(By.id("cnic2")).clear();
	    driver.findElement(By.id("cnic2")).sendKeys(cnic[1]);
	    driver.findElement(By.id("cnic3")).clear();
	    driver.findElement(By.id("cnic3")).sendKeys(cnic[2]);
	    
	    driver.findElement(By.name("official_cell")).clear();
	    driver.findElement(By.name("official_cell")).sendKeys(empData.officialCell);
	    driver.findElement(By.id("official_email")).clear();
	    driver.findElement(By.id("official_email")).sendKeys(empData.officialEmail);
	    
	    WebElement e1 = driver.findElement(By.name("date_of_joining"));
	    e1.clear();
	    e1.sendKeys(empData.dateOfJoining);
	    
	    WebElement e2 = driver.findElement(By.name("career_start_date"));
	    e2.clear();
	    e2.sendKeys(empData.careerStartDate);
	   
	    jse.executeScript("window.scrollBy(0,-700)", "");
	    driver.findElement(By.cssSelector("#UserRole_li > a")).click();
	    
	    wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("roles"))));
	  
	    new Select(driver.findElement(By.id("roles"))).selectByVisibleText(empData.roles);
	    
	    
	    jse.executeScript("window.scrollBy(0,-500)", "");
	    driver.findElement(By.linkText("Skillset")).click();
	    try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    driver.findElement(By.name("ratingMap_0_8")).isSelected();
	    wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.linkText("Development Tools"))));
	    driver.findElement(By.linkText("Development Tools")).click();
	    driver.findElement(By.name("ratingMap_0_9")).isSelected();
	    
	    driver.findElement(By.linkText("Other Information")).click();
	    wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.name("last_institude"))));
	    
	    new Select(driver.findElement(By.name("last_institude"))).selectByVisibleText(empData.lastInstitution);
	    new Select(driver.findElement(By.name("last_degree"))).selectByVisibleText(empData.lastDegree);
	  
	    WebElement e3 = driver.findElement(By.name("degree_completion_date"));
	    e3.clear();
	    e3.sendKeys(empData.degreeCompletionDate);
	    driver.findElement(By.id("add")).click();
	    //Getting Data from database for verification
	    EmpDB obj=new EmpDB();    
		DBEmployee=obj.getAddedRecord(empData.login);
		//Assertions for Comparing Actual and Expected Results
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(DBEmployee.firstName, empData.firstName);
		softAssert.assertEquals(DBEmployee.lastName, empData.lastName);
		softAssert.assertEquals(DBEmployee.cellPersonal, empData.cellPersonal);
		softAssert.assertEquals(DBEmployee.emailPersonal, empData.emailPersonal);
		softAssert.assertEquals(DBEmployee.gender, empData.gender);
		softAssert.assertEquals(DBEmployee.maritalStatus, empData.maritalStatus);
		
		softAssert.assertTrue(empData.officialName.contains(DBEmployee.officialName), "Official Name not Added");
		softAssert.assertEquals(DBEmployee.login, empData.login);
		softAssert.assertEquals(DBEmployee.department, empData.department);
		softAssert.assertEquals(DBEmployee.designation, empData.designation);
		softAssert.assertEquals(DBEmployee.cnic, empData.cnic);
		softAssert.assertEquals(DBEmployee.officialCell, empData.officialCell);
		softAssert.assertEquals(DBEmployee.officialEmail, empData.officialEmail);
		softAssert.assertEquals(DBEmployee.dateOfJoining, empData.dateOfJoining);
		softAssert.assertEquals(DBEmployee.careerStartDate, empData.careerStartDate);
		softAssert.assertEquals(DBEmployee.roles, empData.roles);
		softAssert.assertEquals(DBEmployee.lastDegree, empData.lastDegree);
		softAssert.assertEquals(DBEmployee.lastInstitution, empData.lastInstitution);
		softAssert.assertEquals(DBEmployee.degreeCompletionDate, empData.degreeCompletionDate);
		softAssert.assertAll();
	       
	}
	@AfterTest
    public void afterAddEmpTest() {
        driver.quit();          
    } 
}
