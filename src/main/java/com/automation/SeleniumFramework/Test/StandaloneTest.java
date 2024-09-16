package com.automation.SeleniumFramework.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.hc.core5.util.Asserts;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.automation.SeleniumFramework.Pages.CartPage;
import com.automation.SeleniumFramework.Pages.CheckoutPage;
import com.automation.SeleniumFramework.Pages.ConfirmationPage;
import com.automation.SeleniumFramework.Pages.LoginPage;
import com.automation.SeleniumFramework.Pages.ProductsPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandaloneTest extends BaseTest {

	@Test
	
	 public void submitOrder() throws IOException, InterruptedException{
		 
		 //Rizor123 rizor365@gmail.com
		 String productName="ADIDAS ORIGINAL";
		 ProductsPage productsPage= loginPage.LoginAction("rizor365@gmail.com", "Rizor123");
		 

		 List<WebElement> products=productsPage.getAllProducts();
		 
		 
		 productsPage.addProductToCart(productName);
		 Thread.sleep(2000);
		  CartPage cartPage= productsPage.goToCartPage();
		 Boolean matchBoolean= cartPage.verifyProductName(productName);		
		Assert.assertTrue(matchBoolean);
		CheckoutPage checkoutPage=  cartPage.goToCheckoutPage();
		
		checkoutPage.selectCountry("India");
		checkoutPage.Submit();
		
		
		ConfirmationPage confirmationPage=new ConfirmationPage(driver);
		String finalMessageString=confirmationPage.fetchText();
		Assert.assertEquals(finalMessageString, "THANKYOU FOR THE ORDER.");
		 
	 }
	
	@Test
	public void loginFail() {
		loginPage.LoginAction("rizor365@gmail.com", "Rizr123");
		Assert.assertEquals("Incorrect email or password.", loginPage.getErrorMessage());
		 
		
	}
	
}
