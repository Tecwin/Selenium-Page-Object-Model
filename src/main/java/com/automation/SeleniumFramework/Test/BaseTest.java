package com.automation.SeleniumFramework.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


import com.automation.SeleniumFramework.Pages.LoginPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	public List<HashMap<String, String>> jsonToHashMap(String fileName) throws IOException {
		String jsonString=	FileUtils.readFileToString( new File(fileName),StandardCharsets.UTF_8);
		
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String, String>> list=mapper.readValue(jsonString, new TypeReference<List<HashMap<String,String>>>() {
		});
		return list;
		}
	
	
	public String  getScreenshort(String testCaseName , WebDriver driver) throws IOException {
		TakesScreenshot tScreenshot=(TakesScreenshot)driver;
		File source=tScreenshot.getScreenshotAs(OutputType.FILE);
		File file=new File(System.getProperty("user.dir")+"/reports/"+testCaseName+".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"/reports/"+testCaseName+".png";
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
