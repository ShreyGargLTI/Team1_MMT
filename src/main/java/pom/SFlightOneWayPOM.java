package pom;

import static org.testng.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SFlightOneWayPOM
{
	WebDriver driver;
	// static boolean flagPassengers = false;
	// static boolean flag = false;

	public SFlightOneWayPOM(WebDriver driver)
	{
		this.driver = driver;
	}

	@FindBy(how = How.XPATH, using = "//label[@for='switch__input_1']")
	WebElement oneWay_button;
	@FindBy(how = How.ID, using = "hp-widget__sfrom")
	WebElement From;
	@FindBy(how = How.ID, using = "hp-widget__sTo")
	WebElement To;
	@FindBy(how = How.ID, using = "hp-widget__depart")
	WebElement DepartDate;

	@FindBys(@FindBy(how = How.XPATH, using = "/html/body/div[2]/div[3]/div[3]/div/div[3]/div/div[1]/table/tbody/tr/td"))
	List<WebElement> departDateList;
	@FindBy(how = How.CSS, using = "#hp-widget__paxCounter_pot")
	WebElement Passengers_Class;
	@FindBy(how = How.ID, using = "searchBtn")
	WebElement searchButton;
	@FindBy(how = How.ID, using = "buckets_morning_dep")
	WebElement morningSlot;
	@FindBy(how = How.ID, using = "buckets_noon_dep")
	WebElement noonSlot;
	@FindBy(how = How.ID, using = "buckets_evening_dep")
	WebElement eveningSlot;
	@FindBy(how = How.ID, using = "buckets_night_dep")
	WebElement nightSlot;
	@FindBy(how = How.ID, using = "hp-widget__sTo_error")
	WebElement To_displayError;
	@FindBy(how = How.ID, using = "hp-widget__paxCounter_error")
	WebElement To_displayPassengersError;

	public boolean searchFlight(String from, String to, String Datee,
			String Passenger, String Classs)
			throws InterruptedException, ParseException
	{

		From.click();
		From.sendKeys(from);
		To.click();
		To.sendKeys(to);
		Thread.sleep(1000);
		To.sendKeys(Keys.ENTER);

		Thread.sleep(2000);

		boolean flag1 = verifyDestination(from, to);
		System.out.println("Destination");
		System.out.println(flag1);

		Thread.sleep(2000);

		checkPopup();

		DepartDate.click();

		int day = Integer.parseInt(Datee.substring(0, 2));
		int month = Integer.parseInt(Datee.substring(3, 5));
		int year = Integer.parseInt(Datee.substring(6, 10));
		DepartDateSelect(day, month, year);

		Passengers_Class.click();
		Thread.sleep(2000);

		String adult = Passenger.substring(0, 1);
		String children = Passenger.substring(2, 3);
		String infant = Passenger.substring(4, 5);

		boolean flag2 = Passengers(adult, children, infant, Classs);
		System.out.println("Passengers");
		System.out.println(flag2);
		Thread.sleep(2000);

		return (flag1 || flag2);
		// Thread.sleep(5000);
	}

	boolean verifyDestination(String from, String to)
	{
		boolean flagDestination = false;
		if (from.equals(to))
		{
			if (To_displayError.isEnabled())
			{
				if (To_displayError.isDisplayed())
				{
					String actual_errorDisplay = To_displayError.getText();
					String expected_errorDisplay = "The 'Departure City' and 'Destination City' cannot be same. Please re-type.";
					assertEquals(actual_errorDisplay, expected_errorDisplay);
					flagDestination = true;
				}
			}
		}
		return flagDestination;

	}

	boolean verifyPassenger()
	{
		// System.out.println("verifyPassengers");
		// System.out.println(flag);
		boolean flagPassengers = false;
		if (To_displayPassengersError.isEnabled())
		{
			String actual_passengersErrorDisplay = To_displayPassengersError
					.getText();
			String expected_passengersErrorDisplay = "Total guest count cannot exceed 9";
			assertEquals(actual_passengersErrorDisplay,
					expected_passengersErrorDisplay);
			flagPassengers = true;
		}
		return flagPassengers;
	}

	public boolean Passengers(String adult, String children, String infant,
			String classs)
	{
		boolean flagPassengers = false;
		int noAdult = Integer.parseInt(adult);
		int noChildren = Integer.parseInt(children);
		int noInfant = Integer.parseInt(infant);
		String economy = "Economy", premEco = "Premium Economy",
				business = "Business";
		int num = 0;
		driver.findElement(By.xpath(
				"/html/body/div[2]/div[3]/div[3]/div/div[9]/div[1]/div[1]/div[2]/ul/li["
						+ adult + "]"))
				.click();

		driver.findElement(By.xpath(
				"/html/body/div[2]/div[3]/div[3]/div/div[9]/div[1]/div[2]/div[2]/ul/li["
						+ children + "]"))
				.click();
		if (noAdult + noChildren > 9)
		{
			flagPassengers = verifyPassenger();
		}
		driver.findElement(By.xpath(
				"/html/body/div[2]/div[3]/div[3]/div/div[9]/div[1]/div[3]/div[2]/ul/li["
						+ infant + "]"))
				.click();
		if (noAdult + noChildren + noInfant > 9)
		{
			flagPassengers = verifyPassenger();
		}

		if (classs.equalsIgnoreCase(economy))
		{
			num = 1;
		}
		else if (classs.equalsIgnoreCase(premEco))
		{
			num = 2;
		}
		else if (classs.equalsIgnoreCase(business))
		{
			num = 3;
		}
		driver.findElement(By.xpath(
				"/html/body/div[2]/div[3]/div[3]/div/div[9]/div[2]/ul/li[" + num
						+ "]"))
				.click();
		return flagPassengers;
	}

	void DepartDateSelect(int day, int month, int year)
	{
		int num = (month - 10 + 12 * (year - 2018));
		for (int i = 0; i < num; i++)
		{
			driver.findElement(By.xpath(
					"/html/body/div[2]/div[3]/div[3]/div/div[3]/div/div[2]/div/a/span"))
					.click();
		}
		int total_dates = departDateList.size();

		for (int i = 0; i < total_dates; i++)
		{
			String date;
			String date_price = departDateList.get(i).getText();
			if (date_price.length() >= 2)
			{
				date = date_price.substring(0, 2);
			}
			else
			{
				date = date_price.substring(0, 1);
			}

			if (date.equals("" + day))
			{
				departDateList.get(i).click();
				break;
			}
		}

	}

	public void selectFlight(String journeyTime, String airline)
			throws InterruptedException
	{
		Thread.sleep(10000);
		if (journeyTime.equals("Before 6AM"))
		{
			morningSlot.click();
		}
		else if (journeyTime.equals("6AM-12PM"))
		{
			noonSlot.click();
		}
		else if (journeyTime.equals("12PM-6PM"))
		{
			eveningSlot.click();
		}
		else if (journeyTime.equals("After 6AM"))
		{
			nightSlot.click();
		}
		driver.findElement(By
				.xpath("(//span[contains(.," + "'" + airline + "'" + ")])[2]"))
				.click();
		Thread.sleep(4000);
	}

	public void selectFlight(String journeyTime) throws InterruptedException
	{
		Thread.sleep(10000);
		if (journeyTime.equals("Before 6AM"))
		{
			morningSlot.click();
		}
		else if (journeyTime.equals("6AM-12PM"))
		{
			noonSlot.click();
		}
		else if (journeyTime.equals("12PM-6PM"))
		{
			eveningSlot.click();
		}
		else if (journeyTime.equals("After 6AM"))
		{
			nightSlot.click();
		}
		Thread.sleep(4000);

		// String totalFlightsString =
		// driver.findElement(By.xpath("/html/body/div[5]/div/div[6]/div[5]/div[2]/div[2]/div/div/div/p[2]/span")).getText();
		// int totalFlights = Integer.parseInt(totalFlightsString.substring(0,
		// 2));

		for (int i = 0; i < 10; i++)
		{
			int num = i + 5;
			String Price = driver
					.findElement(By.xpath(
							"/html/body/div[5]/div/div[6]/div[5]/div[2]/div["
									+ num + "]/div/div[2]/div[6]/p[1]/span[2]"))
					.getText();
			System.out.println(Price);
		}
		Thread.sleep(2000);
		driver.findElement(By.xpath(
				"/html/body/div[5]/div/div[6]/div[5]/div[2]/div[5]/div/div[2]/div[7]/p/a"))
				.click();
	}

	public void checkPopup()
	{
		try
		{
			WebElement popup = driver.findElement(By.id("webpush-bubble"));
			if (popup.isDisplayed())
			{
				driver.switchTo().frame(popup);
				driver.findElement(By.id("deny")).click();
				driver.switchTo().defaultContent();
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

	public void search() throws InterruptedException
	{
		searchButton.click();
		Thread.sleep(4000);
	}

}