package pages;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HotelBookingPage {
	public void bookHotel(WebDriver driver){
		ReadRepository readRepo = new ReadRepository();
		String repoName = "booking.properties";
		Properties prop = null;
		try {
			prop = readRepo.getPropertyObj(repoName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.xpath(prop.getProperty("hotels"))).click();
		driver.findElement(By.xpath(prop.getProperty("hotelSearch"))).click();
		driver.findElement(By.xpath(prop.getProperty("hotelCheckIn"))).click();
		driver.findElement(By.xpath(prop.getProperty("hotelCheckOut"))).click();
		driver.findElement(By.xpath(prop.getProperty("hotelGuestNo"))).click();
		driver.findElement(By.xpath(prop.getProperty("serachBtn"))).click();
	}
}
