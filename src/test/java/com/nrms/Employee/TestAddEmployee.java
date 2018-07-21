package com.nrms.Employee;

import java.sql.Connection;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nrms.DataReader.PropertiesReader;
import com.nrms.loginspecs.LoginManager;
import com.nrmswebdriverconfigration.NRMSConstants;

public class TestAddEmployee {
	WebDriver driver= new FirefoxDriver();
	Employee empData=new Employee();
	@Parameters(value="baseURL")
	@BeforeTest
	public void login(String baseURL)
	{
		
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
	@Parameters(value="file")
	@Test
	public void add(String file) throws Exception
	{
		try{
			empData=DataClass.getData (file);
		}
		catch(Exception e){
			System.out.println(e.getStackTrace());
		}
	
		
		WebDriverWait wait = new WebDriverWait(driver,3200);
	    wait.until(ExpectedConditions.visibilityOfElementLocated((By.id("fname"))));
		//Thread.sleep(10000);
	    driver.findElement(By.id("fname")).sendKeys(empData.firstName);
	    
	    driver.findElement(By.id("lname")).sendKeys(empData.lastName);
	    
	    driver.findElement(By.name("cell_personal")).sendKeys(empData.cellPersonal);
	    driver.findElement(By.name("email_personal")).sendKeys(empData.emailPersonal);
	    driver.findElement(By.name("gender")).sendKeys(empData.gender);
	    new Select(driver.findElement(By.name("marital_status"))).selectByVisibleText(empData.meritalStatus);
	    //driver.findElement(By.name("profile_picture")).clear();
	    driver.findElement(By.name("profile_picture")).sendKeys(empData.profilePicture);
	    
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
	    
	    driver.findElement(By.cssSelector("span.input-group-addon")).click();
	    
	    driver.findElement(By.id("official_email")).clear();
	    driver.findElement(By.id("official_email")).sendKeys(empData.officialEmail);
	    
	    WebElement e1 = driver.findElement(By.name("date_of_joining"));
	    jse.executeScript("arguments[0].setAttribute('value','" +empData.dateOfJoining +"')",e1);
	    
	    WebElement e2 = driver.findElement(By.name("career_start_date"));
	    jse.executeScript("arguments[0].setAttribute('value','" +empData.careerStartDate +"')",e2);
	     
	    driver.findElement(By.name("resume")).sendKeys(empData.resume);
	    System.out.println("Show3");
	    
	    Thread.sleep(2000);
	    jse.executeScript("window.scrollBy(0,-700)", "");
	    driver.findElement(By.cssSelector("#UserRole_li > a")).click();
	    
	    wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("roles"))));
	  
	    new Select(driver.findElement(By.id("roles"))).selectByVisibleText(empData.roles);
	    
	    
	    jse.executeScript("window.scrollBy(0,-500)", "");
	    driver.findElement(By.linkText("Skillset")).click();
	   
	    driver.findElement(By.name("ratingMap_0_8")).click();
	    driver.findElement(By.name("ratingMap_0_16")).click();
	    driver.findElement(By.linkText("Development Tools")).click();
	    driver.findElement(By.name("ratingMap_0_9")).click();
	    driver.findElement(By.name("ratingMap_0_10")).click();
	    driver.findElement(By.name("ratingMap_0_11")).click();
	    
	    jse.executeScript("window.scrollBy(0,-500)", "");
	    
	    
	    driver.findElement(By.linkText("Other Information")).click();
	 
	    new Select(driver.findElement(By.name("last_institude"))).selectByVisibleText(empData.lastInstitution);
	    new Select(driver.findElement(By.name("last_degree"))).selectByVisibleText(empData.lastDegree);
	  
	    WebElement e3 = driver.findElement(By.name("degree_completion_date"));
	    jse.executeScript("arguments[0].setAttribute('value','" +empData.degreeCompletionDate +"')",e3);

	    driver.findElement(By.id("add")).click();
	    //Getting Data from database for verification
	    //This code is commented because it will be used when Assertion will be applied
	    
	    /*DBConnection db=new DBConnection();
		Connection con=db.connect();
		db.getRecord(con, "Kamran Khan");
		db.close(con);
	   */
	    
	}
}
