package com.automation.SeleniumFramework.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.SeleniumFramework.Utility.Utility;

public class LoginPage extends Utility{
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements( driver,this);
	}
	
	@FindBy(id ="userEmail")
	WebElement email;
	

	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(xpath = "//input[@id='login']")
	WebElement login;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	
	 public ProductsPage LoginAction(String username,String secretkey) {
		 
		 email.sendKeys(username);
		 password.sendKeys(secretkey);
		 login.click();
		 return new ProductsPage(driver);
	 }
	 
	 public String getErrorMessage()
	 {
		 waitForElement(errorMessage);
		 return errorMessage.getText();
	 }
	 public void goTo() {
		 driver.get("https://rahulshettyacademy.com/client/");
	 }

}
