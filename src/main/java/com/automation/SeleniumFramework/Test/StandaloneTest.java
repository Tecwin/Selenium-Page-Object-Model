package com.automation.SeleniumFramework.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.automation.SeleniumFramework.Pages.CartPage;
import com.automation.SeleniumFramework.Pages.CheckoutPage;
import com.automation.SeleniumFramework.Pages.ConfirmationPage;
import com.automation.SeleniumFramework.Pages.LoginPage;
import com.automation.SeleniumFramework.Pages.OrderPage;
import com.automation.SeleniumFramework.Pages.ProductsPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandaloneTest extends BaseTest {
	
	String productName="ADIDAS ORIGINAL";

	@Test(dataProvider = "getData",groups= {"purchase"})
	
	 public void submitOrder(HashMap<String ,String> map) throws IOException, InterruptedException{ 
		 //when using just obect as data provider then we use String email,String password,String productName to retrieve individual values
		 //Rizor123 rizor365@gmail.com
		 
		 ProductsPage productsPage= loginPage.LoginAction(map.get("email"), map.get("password"));
		 

		 List<WebElement> products=productsPage.getAllProducts();
		 
		 
		 productsPage.addProductToCart(map.get("productName"));
		 Thread.sleep(2000);
		  CartPage cartPage= productsPage.goToCartPage();
		 Boolean matchBoolean= cartPage.verifyProductName(map.get("productName"));		
		Assert.assertTrue(matchBoolean);
		CheckoutPage checkoutPage=  cartPage.goToCheckoutPage();
		
		checkoutPage.selectCountry("India");
		checkoutPage.Submit();
		
		
		ConfirmationPage confirmationPage=new ConfirmationPage(driver);
		String finalMessageString=confirmationPage.fetchText();
		Assert.assertEquals(finalMessageString, "THANKYOU FOR THE ORDER.");
		 
	 }
	
	
	@Test(dependsOnMethods = {"submitOrder"})
	public void orderHistory() {
		
		ProductsPage productsPage= loginPage.LoginAction("rizor365@gmail.com", "Rizor123");
		OrderPage orderPage= productsPage.goToOrdersPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName)); 
		
	}
	
	@DataProvider
	public Object[][] getData(){
	//	return new Object[][]{{"rizor365@gmail.com","Rizor123","ADIDAS ORIGINAL"}}; this is correct , other way is using HashMap
		HashMap<String, String> map=new HashMap<>();
		map.put("email", "rizor365@gmail.com");
		map.put("password", "Rizor123");
		map.put("productName", "ADIDAS ORIGINAL");
		return new Object[][]{{map}};
	}
	
}
