package testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.Browser;
import pages.HotelBookingPage;
import pages.LoginPage;
import pages.LoginPageSample;

public class LoginTest {
	Browser browser;
	WebDriver driver;
	
	@BeforeTest
	public void setUp(){
		System.out.println("Inside login.java -> setUp() before test");
		browser= new Browser();
		driver =  browser.getDriver();
		driver.get("https://www.phptravels.net/");
	}
	
	@Test(enabled = false)
	public void loginDemoTest() {
		System.out.println("Inside login.java -> login() test");
		LoginPageSample loginPageDemo = new LoginPageSample();
		loginPageDemo.loginDemo(driver);
	}
	
	@Test(enabled = false)
	public void login(){
		System.out.println("inside test");
		LoginPage loginPage = new LoginPage();
		loginPage.login(driver);
	}
	
	@Test
	public void bookHotelTest(){
		HotelBookingPage hotelbook = new HotelBookingPage();
		hotelbook.bookHotel(driver);
	}
	
	@AfterTest
	public void cleanUp(){
		System.out.println("Inside login.java -> cleanUp() after test");
		//driver.close();
	}

}
