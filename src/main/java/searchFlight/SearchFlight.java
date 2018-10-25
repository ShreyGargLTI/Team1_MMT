package searchFlight;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;

import pom.BookingGuestPOM;
import pom.BookingGuestPOM1;
import pom.SFlightMultiCityPOM;
import pom.SFlightOneWayPOM;
import pom.SFlightTwoWayPOM;
import util.BrowserFactory;
import util.CheckPopup;
import util.Constants;
import util.ReadExcel;

public class SearchFlight
{
	WebDriver driver;

	@Test(dataProvider = "oneWaySearch")
	public void oneWaySearch(String from, String to, String DepartDate,
			String passengers, String Classs)
			throws InterruptedException, ParseException
	{
		boolean flag = false;
		driver = BrowserFactory.startBrowser(Constants.FIREFOX);
		driver.get(Constants.BASE_URL);
		
		checkPop();
		
		SFlightOneWayPOM oneWayPage = PageFactory.initElements(driver,
				SFlightOneWayPOM.class);
		
		
		flag = oneWayPage.searchFlight(from, to, DepartDate, passengers,
				Classs);
		if (!flag)
		{
			oneWayPage.search();
		}
		if (!flag)
		{
			oneWayPage.selectFlight("6AM-12PM");

//			bookFlight(getPassengerNo(passengers));
		}

	}

	@DataProvider(name = "oneWaySearch")
	public String[][] readDataOneWay() throws FileNotFoundException, IOException
	{
		return (ReadExcel.readoneWay(Constants.PATH_EXCEL_ONEWAY));
	}

	@Test(dataProvider = "twoWaySearch")
	public void twoWaySearch(String from, String to, String sDate, String eDate,
			String passengers, String classs) throws InterruptedException
	{
		boolean flag = false;
		driver = BrowserFactory.startBrowser(Constants.FIREFOX);
		driver.get(Constants.BASE_URL);
		
		checkPop();
		
		SFlightTwoWayPOM twoWayPage = PageFactory.initElements(driver,
				SFlightTwoWayPOM.class);
		flag = twoWayPage.searchFlight(from, to, sDate, eDate, passengers,
				classs);

//		bookFlight(getPassengerNo(passengers));
		System.out.println(flag);

		if (!flag)
		{
			twoWayPage.search();
		}
		if (!flag)
		{
			twoWayPage.selectFlight("6AM-12PM", "12PM-6PM");

		}
	}

	@DataProvider(name = "twoWaySearch")
	public String[][] readDataTwoWay() throws FileNotFoundException, IOException
	{
		return (ReadExcel.readTwoWay(Constants.PATH_EXCEL_TWOWAY));
	}

	@Test()
	public void multiCitySearch() throws InterruptedException
	{
		boolean flag = false;
		driver = BrowserFactory.startBrowser(Constants.FIREFOX);
		driver.get(Constants.BASE_URL);
		
		checkPop();
		
		SFlightMultiCityPOM multiCityPage = PageFactory.initElements(driver,
				SFlightMultiCityPOM.class);
		if (!flag)
		{
			flag = multiCityPage.searchFlight("bombay", "delhi", "Pune",
					"12-01-2019", "06-02-2019", "5-3-1", "Economy");
		}
		// System.out.println(flag);
		if (!flag)
		{
			flag = multiCityPage.addCity("Pune", "02-03-2019");
		}
		if (!flag)
		{
			multiCityPage.search();
		}
		if (!flag)
		{
			multiCityPage.selectFlight();
		}
	}

//	private void bookFlight(int[] passNo) throws InterruptedException
//	{
//		BookingGuestPOM bookPage = PageFactory.initElements(driver,
//				BookingGuestPOM.class);
//		bookPage.bookFlight("dfghdfthdfg@gmail.com");
//
//		BookingGuestPOM1 bookPage1 = PageFactory.initElements(driver,
//				BookingGuestPOM1.class);
//
//		bookPage1.adultDetails("name", "Lname", passNo[0], passNo[1],
//				passNo[2]);
//		
//		bookPage1.childDetails("name", "Lname", passNo[0], passNo[1],
//				passNo[2], "5");
//		
//		bookPage1.infantDetails("name", "Lname", passNo[0], passNo[1],
//				passNo[2]);
//	}
//
//	private int[] getPassengerNo(String passengers)
//	{
//		int[] res = new int[3];
//
//		String[] arr = passengers.split("-");
//
//		for (int i = 0; i < res.length; i++)
//		{
//			res[i] = Integer.parseInt(arr[i]);
//		}
//
//		return res;
//	}
	
	public void checkPop()
	{
		CheckPopup cPopup = new CheckPopup(driver, Thread.currentThread());
		Thread thread = new Thread(cPopup);
		thread.start();
	}
	
	@AfterTest
	public void close()
	{
		driver.quit();
	}
}