package pages;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	public void login(WebDriver driver){
		ReadRepository readRepo = new ReadRepository();
		String repoName = "login.properties";
		Properties prop = null;
		try {
			prop = readRepo.getPropertyObj(repoName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebElement ele =  driver.findElement(By.xpath(prop.getProperty("myAccount")));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();",ele);
		/*if(driver instanceof JavascriptExecutor){
			js.executeScript("arguments[0].click();",ele);
			System.out.println("javascript exe");
		}*/
		
		ele = driver.findElement(By.xpath(prop.getProperty("login")));
		js.executeScript("arguments[0].click();",ele);
		
		
	}
}
