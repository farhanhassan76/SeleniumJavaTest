package com.nrms.RolesSpecs;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.nrms.URLValidator.URLValidator;
import com.nrmswebdriverconfigration.LoadConfigration;
import com.nrmswebdriverconfigration.NRMSConstants;


public class TestRole {
	private WebDriver driver; 
	
	public void LoadDriver() {  
	  	LoadConfigration config=new LoadConfigration("GoogleChrome");
			driver=config.LoadWebDriver();
	  	
	  } 
	
	
	
	public boolean testAnyRole(String [] roles_list, String user_name , String password )
	{
		String user=user_name;
		String pass=password;
		callLogin(user,pass);
		
        Actions actions = new Actions(driver);
     
		for(int index=0;index<roles_list.length;index++)
		{
		System.out.println("========================="+index+"\t"+roles_list[index]+"========================");
		
		WebElement	click_1=driver.findElement(By.linkText(roles_list[index]));
		if(click_1!=null)
		{
			actions.moveToElement(click_1).click().perform();
		    List<WebElement> roles =driver.findElements(By.cssSelector("li.droplink.open > ul.sub-menu > li > a"));
		    System.out.println(roles.size());
		    for (int indexi=0;indexi<roles.size();indexi++)
		          {
			        System.out.println(""+roles.get(indexi).getAttribute("href"));
			        boolean b=new URLValidator().checkIfURLExists(roles.get(indexi).getAttribute("href"));;
			        if(b==false)
			        {
			        	System.out.println("Unable to Find Page \t"+roles.get(indexi).getText()+"\t"+roles.get(indexi).getAttribute("href"));
			        	 driver.quit();
			        	return false;
			        	
			        }	
			        
			       
			        // Assert.assertTrue("Unable to Find Page"+roles.get(indexi).getText()+""+roles.get(indexi).getAttribute("href"),b==true);
		          }
		}
		
		}
		 driver.quit(); 
		return true;
	}
	
				public void callLogin(String username, String Password)
				{
					LoadDriver();
					driver.get(NRMSConstants.SERVER_URL);  
			  	    WebElement email=driver.findElement(By.id("username"));
			        email.sendKeys(username);
			        WebElement password=driver.findElement(By.id("password"));
			        password.sendKeys(Password);
			        driver.findElement(By.tagName("button")).click();
				}
}
