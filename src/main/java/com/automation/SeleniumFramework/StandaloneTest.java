package com.automation.SeleniumFramework;

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

import com.automation.SeleniumFramework.Pages.CartPage;
import com.automation.SeleniumFramework.Pages.CheckoutPage;
import com.automation.SeleniumFramework.Pages.ConfirmationPage;
import com.automation.SeleniumFramework.Pages.LoginPage;
import com.automation.SeleniumFramework.Pages.ProductsPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandaloneTest {

	 public static void main(String []args) throws InterruptedException {
		 
		 //Rizor123 rizor365@gmail.com
		 String productName="ADIDAS ORIGINAL";
		 
		 WebDriverManager.chromedriver().setup();
		 
		 ChromeOptions options=new ChromeOptions();
		 options.addArguments("--disable-search-engine-choice-screen");
		 WebDriver driver=new ChromeDriver(options);
		 driver.manage().window().maximize();
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		 driver.get("https://rahulshettyacademy.com/client");
		 
		 LoginPage loginPage=new LoginPage(driver);
		 loginPage.goTo();
		 ProductsPage productsPage= loginPage.LoginAction("rizor365@gmail.com", "Rizor123");
		 

		 List<WebElement> products=productsPage.getAllProducts();
		 
		 
		 productsPage.addProductToCart(productName);
		 //Thread.sleep(2000);
		 productsPage.goToCartPage();
		 
		 
		 CartPage cartPage=new CartPage(driver);
		 Boolean matchBoolean= cartPage.verifyProductName(productName);
		 
		
		Assert.assertTrue(matchBoolean);
		cartPage.goToCheckoutPage();
		

		CheckoutPage checkoutPage=new CheckoutPage(driver);
		checkoutPage.selectCountry("India");
		checkoutPage.Submit();
		
		
		ConfirmationPage confirmationPage=new ConfirmationPage(driver);
		String finalMessageString=confirmationPage.fetchText();
		Assert.assertEquals(finalMessageString, "THANKYOU FOR THE ORDER.");
		
		
		
		
		 driver.close();
	 }
	
}
