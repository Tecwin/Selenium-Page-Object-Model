package com.automation.SeleniumFramework.Utility;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automation.SeleniumFramework.Pages.CartPage;

public class Utility {
	
	WebDriver driver;
	
	
	
	public Utility(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css="button[routerlink*='cart']")
	WebElement cartButton;

	
	By cartButtonBy=By.cssSelector("button[routerlink*='cart']");
	public void waitForElementToAppear(By findBy) {
	
	WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	
	}
	
	public void waitForElement(WebElement findBy) {
		
	WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOf(findBy));
	
	}
	
	public void waitForElementToDisapper(WebElement element) {
		WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf((element)));
	}

	public CartPage goToCartPage() {
		waitForElementToAppear(cartButtonBy);
		cartButton.click();
		return new CartPage(driver);
	}
	
	public void enterText(WebElement element,String text) {
		Actions actions =new Actions(driver);
		actions.sendKeys(element,text).build().perform();
	}
	public String getText(WebElement element) {
		return element.getText();
	}
	
}
