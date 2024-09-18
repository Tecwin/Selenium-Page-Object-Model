package com.automation.SeleniumFramework.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.PublicKey;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.automation.SeleniumFramework.Pages.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public WebDriver driver;
	
	public LoginPage loginPage;
	
	public WebDriver intializeDriver() throws IOException {
		
		Properties properties=new Properties();
		FileInputStream fileInputStream =new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/automation/SeleniumFramework/Resources/GlobalData.properties");
		properties.load(fileInputStream);
		String browserName= properties.getProperty("browser");
		if(browserName.equalsIgnoreCase("chrome")) {
			
		
		WebDriverManager.chromedriver().setup();
		 
		
		 ChromeOptions options=new ChromeOptions();
		 options.addArguments("--disable-search-engine-choice-screen");
		 driver=new ChromeDriver(options);

		 driver.get("https://rahulshettyacademy.com/client");
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			
		}
		else if(browserName.equalsIgnoreCase("edge")) {
			
		}
		 driver.manage().window().maximize();
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		 return driver;
		 

	}
	
	 @BeforeMethod(alwaysRun = true)
	 public LoginPage launchApplication() throws IOException {
		 driver=intializeDriver();
		 loginPage=new LoginPage(driver);
		 loginPage.goTo();
		 return new LoginPage(driver);
		 
	 }
	 
	 @AfterMethod(alwaysRun = true)
	 public void tearDown() {
		 driver.close();
	 }
}
