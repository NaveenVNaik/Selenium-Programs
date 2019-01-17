package com.pages.nav;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Browser {

	public WebDriver getDriver() {
		ReadRepository repo = new ReadRepository();
		String repoName = "browser.properties";
		Properties prop = null;
		try {
			prop = repo.getPropertyObj(repoName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.setProperty(prop.getProperty("driverName"), prop.getProperty("driverPath"));
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		return driver;
	}

}
