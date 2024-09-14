package com.automation.SeleniumFramework.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.SeleniumFramework.Utility.Utility;

public class CheckoutPage extends Utility{
	
	WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy( xpath =  "//input[@placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css=".ta-results button:nth-child(3)")
	WebElement selectCountry;
	
	@FindBy(css=".action__submit")
	WebElement submit;
	
	By results=By.cssSelector(".ta-results");
	
	public void selectCountry(String countryName) {
		
		enterText(country, countryName);
		waitForElementToAppear(results);
		selectCountry.click();
	}
	
	public void Submit() {
		submit.click();
	}
	
	
	

}
