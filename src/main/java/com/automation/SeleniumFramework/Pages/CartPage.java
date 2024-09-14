package com.automation.SeleniumFramework.Pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.SeleniumFramework.Utility.Utility;

public class CartPage extends Utility{
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}

	@FindBy(xpath = "//div[@class=\"cartSection\"]/h3")
	List<WebElement> cartProducts;
	
	@FindBy(xpath = "//li[@class='totalRow']/button")
	WebElement checkoutButton;
	
	public Boolean verifyProductName(String productName) {
		return  cartProducts.stream().anyMatch(product->product.getText().equals(productName));
	}
	
	public void goToCheckoutPage() {
		checkoutButton.click();
	}
	
	

}
