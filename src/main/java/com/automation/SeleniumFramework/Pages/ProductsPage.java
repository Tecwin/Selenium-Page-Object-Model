package com.automation.SeleniumFramework.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import com.automation.SeleniumFramework.Utility.Utility;

public class ProductsPage extends Utility {
	
	WebDriver driver;
	public ProductsPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".mb-3")
	List<WebElement> products;
	
	
	@FindBy(css=".ng-animating")
	WebElement animation;
	
	By productsBy=By.cssSelector(".mb-3");
	By addToCartBy =By.cssSelector(".card-body button:last-of-type");
	
	By toastContainerBy=By.cssSelector("#toast-container");
	
	
	
	
	
	
	public List<WebElement> getAllProducts(){
		waitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement SelectProduct(String productName) {
		 WebElement selectedProduct=getAllProducts().stream().filter(s->s.findElement(By.xpath(".//b")).getText().equals(productName)).findFirst().orElse(null);
		 return selectedProduct;
	}
	
	public void addProductToCart(String productName) {
		SelectProduct(productName).findElement(addToCartBy).click();
		waitForElementToAppear(toastContainerBy);
		waitForElementToDisapper(animation);
	}
	

}
