package com.nrms.loginspecs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Logout {
	
	private WebDriver driver;
	
	public Logout(WebDriver driver)
	{
		this.driver=driver;
	}
   
	 public void callLogout()
	   {
		   driver.findElement(By.xpath("html/body/main/div[1]/div/div[4]/div/ul[2]/li[2]/a/span")).click();
	   }
}
   