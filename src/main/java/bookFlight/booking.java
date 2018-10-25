package bookFlight;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import pom.BookingGuestPOM;
import pom.BookingGuestPOM1;
import util.BrowserFactory;
import util.Constants;

public class booking
{
	WebDriver driver;
	
	@Test
	public void bookTest() throws InterruptedException
	{

		 driver = BrowserFactory.startBrowser(Constants.FIREFOX);
		 String
		 s="https://flights.makemytrip.com/makemytrip/review/R/R/E/3/1/1/BOM_DEL_13-12-2018,DEL_BOM_02-01-2019/0-false-3227-3227-false-false|BOM-DEL-6E-6E-6489-false-false-false-1544640600000-1544649000000-2292-1190-60-732-JFIP6489-J-6EUNI4:0-false-4245-4245-false-false|DEL-BOM-6E-6E-171-false-false-false-1546384500000-1546393200000-3386-1190-60-644-VFIP171-V-6EUNI4/127a3980384|575c|b02b|cc5c|159e74d/odc/asyncResponsiveReview#/review";
		 driver.get(s);
		 BookingGuestPOM bookPage = PageFactory.initElements(driver,
		 BookingGuestPOM.class);
		 bookPage.bookFlight("dfghdfthdfg@gmail.com");
		
		 BookingGuestPOM1 bookPage1 = PageFactory.initElements(driver,
		 BookingGuestPOM1.class);
		
//		 bookPage1.adultDetails( firstname, lastname, phone,  noAdult,  child,  infant);
//		 
//		 bookPage1.adultDetails(("shivam", lastname, phone, noAdult, child, infant);

	}

	public void bookingDetails(WebDriver driver) throws InterruptedException
	{
		BookingGuestPOM1 bookPage = PageFactory.initElements(driver,
				BookingGuestPOM1.class);
		// bookPage.searchFlight("himanshu", "singh", "9876543210");
	}

}
