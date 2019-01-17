package com.pages.nav;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPageSample {
	public void loginDemo(WebDriver driver){
		ReadRepository readRepo = new ReadRepository();
		String repoName = "login.properties";
		Properties prop = null;
		try {
			prop = readRepo.getPropertyObj(repoName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("noThanksBtn"))));
		driver.findElement(By.xpath(prop.getProperty("noThanksBtn"))).click();
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("closeSignUpBtn"))));
		int size = driver.findElements(By.tagName("iframe")).size();
		System.out.println("total"+size);
		//boolean found = false;
		for(int i=0; i< size; i++){
			try{
				driver.findElement(By.xpath(prop.getProperty("emailInput"))).sendKeys("abcvv792@rtrt.com");
				driver.findElement(By.xpath(prop.getProperty("submitBtn"))).click();	
			}catch(NoSuchElementException nse) {
				System.out.println("one");
			}
		    driver.switchTo().defaultContent();
		}
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.switchTo().defaultContent();
		size = driver.findElements(By.tagName("iframe")).size();
		System.out.println("total"+size);
		for(int i=0; i< size; i++){
			driver.switchTo().frame(i);
			try{
				driver.findElement(By.xpath(prop.getProperty("loginURL"))).click();
			}catch(NoSuchElementException nse){
				System.out.println("two");
			}
			driver.switchTo().defaultContent();
		}
		//wait.until(ExpectedConditions.elementToBeClickable(By.xpath(prop.getProperty("loginURL"))));
		//driver.findElement(By.xpath(prop.getProperty("loginURL"))).click();
	}
}
