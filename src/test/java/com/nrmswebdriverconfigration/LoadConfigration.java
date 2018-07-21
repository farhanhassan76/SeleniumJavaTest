package com.nrmswebdriverconfigration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class LoadConfigration {
	protected WebDriver driver;
	private  final String path_chrome_driver="selenium-jars/chromedriver.exe";
	private String Browser;
	public LoadConfigration(String Browser)
			{
				setBrowser(Browser);
			}

	public void setBrowser(String Browser)   
		{
			this.Browser=Browser;
		}
	public String getBrowser()
	{
		return Browser;
	}

	public WebDriver LoadWebDriver()
		{
		if(getBrowser().equals("GoogleChrome"))
		{
			System.setProperty("webdriver.chrome.driver", path_chrome_driver); 
			 driver = new ChromeDriver();
		}
			 
		else if(getBrowser().equals("FireFox"))
		{
			driver=new FirefoxDriver();
		}
		
		
		return driver;
		}
	
}
