package com.automation.SeleniumFramework.Test;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.automation.SeleniumFramework.Pages.CartPage;
import com.automation.SeleniumFramework.Pages.ProductsPage;
import com.automation.SeleniumFramework.Utility.Retry;

public class NegativeScenarios extends BaseTest {
	
	@Test
	
	 public void validateProductNameIncart() throws IOException, InterruptedException{
		 
		 //Rizor123 rizor365@gmail.com
		 String productName="ADIDAS ORIGINAL";
		 ProductsPage productsPage= loginPage.LoginAction("rizor365@gmail.com", "Rizor123");
		 

		 List<WebElement> products=productsPage.getAllProducts();
		 
		 
		 productsPage.addProductToCart(productName);
		 Thread.sleep(2000);
		  CartPage cartPage= productsPage.goToCartPage();
		 Boolean matchBoolean= cartPage.verifyProductName(productName+"s");		
		Assert.assertFalse(matchBoolean);
		
		 
	 }
	
	@Test(groups = "negativeCases",retryAnalyzer = Retry.class)
	public void loginFail() {
		loginPage.LoginAction("rizor365@gmail.com", "Rizr123");
		Assert.assertEquals("Incorrect email or passwor.", loginPage.getErrorMessage());
		 
		
	}

}
