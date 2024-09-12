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
		 driver.findElement(By.id("userEmail")).sendKeys("rizor365@gmail.com");
		 driver.findElement(By.id("userPassword")).sendKeys("Rizor123");
		 driver.findElement(By.xpath("//input[@id='login']")).click();
		 WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(5));
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		 List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
		 
		 WebElement selectedProduct=products.stream().filter(s->s.findElement(By.xpath(".//b")).getText().equals(productName)).findFirst().orElse(null);
		selectedProduct.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		 wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		 driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();
		 
		 List<WebElement> cartProducts=driver.findElements(By.xpath("//div[@class=\"cartSection\"]/h3"));
		Boolean matchBoolean= cartProducts.stream().anyMatch(product->product.getText().equals(productName));
		Assert.assertTrue(matchBoolean);
		driver.findElement(By.xpath("//li[@class='totalRow']/button")).click();
		
		Actions actions =new Actions(driver);
		actions.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")),"India").build().perform();
		
		driver.findElement(By.cssSelector(".ta-results button:nth-child(3)")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String finalMessageString=driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertEquals(finalMessageString, "THANKYOU FOR THE ORDER.");
		
		
		
		
		 driver.close();
	 }
	
}
