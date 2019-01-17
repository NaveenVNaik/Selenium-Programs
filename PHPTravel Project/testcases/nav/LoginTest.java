package com.testcases.nav;

import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.pages.nav.Browser;
import com.pages.nav.HotelBookingPage;
import com.pages.nav.LoginPage;
import com.pages.nav.LoginPageSample;
import com.pages.nav.XcelRead;

public class LoginTest {
	Browser browser;
	WebDriver driver;
	
	@BeforeTest
	public void setUp(){
		System.out.println("Inside login.java -> setUp() before test");
//		browser= new Browser();
//		driver =  browser.getDriver();
//		driver.get("https://www.phptravels.net/");
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
	public void xcelReadTest(){
		System.out.println("inside xcelReadTest");
		Sheet xcelSheet = null;
		XcelRead xcelRead = new XcelRead();
		try {
			xcelSheet = xcelRead.getXcelSheet("C:\\Users\\NNNAIK\\Automation Projects\\PHPTravelAutomation\\src\\com\\testdata\\nav",
					"Test Data.xlsx", "Login Credentials");
		} catch (IOException e) {
			System.out.println("Exception occurred");
		}
		
	    int rowCount = xcelSheet.getLastRowNum()-xcelSheet.getFirstRowNum();

	    for (int i = 0; i < rowCount+1; i++) {
	    	Row row = xcelSheet.getRow(i);
	    	if(i!=0){
		        for (int j = 0; j < row.getLastCellNum(); j++) {
		        	if(j==0 && (row.getCell(j).toString().equals("no"))){	
		        		break;
		        	}
		        	System.out.print(row.getCell(j).toString()+"|| ");
		        }
		        System.out.println();
	    	}else{
	    		//System.out.print(row.getCell(0).getStringCellValue()+"|| ");
	    	}
	    }
	}
	
	@Test (enabled = false)
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
